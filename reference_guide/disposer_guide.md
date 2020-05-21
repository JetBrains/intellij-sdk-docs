---
title: The Disposer and Disposables
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform's [`Disposer`](upsource:///platform/util/src/com/intellij/openapi/util/Disposer.java) facilitates resource cleanup.
If a subsystem keeps a set of resources alive coincident with a parent object's lifetime, the subsystem's resources should be registered with the `Disposer` to be released before or at the same time as the parent object.  

The `Disposer` is a singleton that manages a tree of [`Disposable`](upsource:///platform/util/src/com/intellij/openapi/Disposable.java) instances. 
A `Disposable` is an interface for any object providing a `Disposable.dispose()` method to release heavyweight resources after a specific lifetime.

The `Disposer` supports chaining `Disposables` in parent-child relationships.
However, as discussed below, the choice of parent is essential to the performance of the IDE.

* bullet list
{:toc}

## The Disposer Singleton
The primary purpose of the [`Disposer`](upsource:///platform/util/src/com/intellij/openapi/util/Disposer.java) singleton is to enforce the rule that _a child `Disposable` never outlives its parent_. 
The `Disposer` organizes `Disposable` objects in a tree of parent-child relationships.
The tree of `Disposable` objects ensures the `Disposer` releases children of a parent first. 

See [The Disposable Interface](#the-disposable-interface) for more information about creating `Disposable` classes. 

### Choosing a Disposable Parent
> **Note** **-- Editorial -- @yole may choose to address this topic in more depth here, below in the Google doc, or on a separate page.**

To register a child `Disposable`, a parent `Disposable` of a suitable lifetime is used to establish the parent-child relationship.
One of the parent `Disposables` provided by the IntelliJ Platform can be chosen, or it can be another `Disposable`.

There might be a finer-grained parent for a child being registered with the `Disposer`. 
A few suggested approaches to identify a parent are:
- If it's a custom component, implement `Disposable` for it, and the `Disposable.dispose()` body can be left empty.
  This approach eliminates [implicit references to the enclosing instance](#how-parent-subscriptions-may-cause-problems).
- If some of the objects in use already implement `Disposable`, use one of them as a parent if their lifetime is greater than, or equal to, the child objects.
- For very fine-grained control, create a new `Disposable` using `Disposer.newDisposable()` that is manually disposed at the end of
  the `Disposable.dispose()` method.

See the [Background](#background) section for more information about choosing parent `Disposable` objects.

### Adding Children to a Parent
Plugins can make their `Disposable` component known to the IDE at runtime by using:

```java
  Disposer.register(parentDisposable, childDisposable)
```
This snippet registers the `childDisposable` as a child of the (already registered) `parentDisposable`.

### Determining Disposal Status
You can use `Disposer.isDisposed()` to check whether a `Disposable` has already been disposed. 
This check is useful, for example, for an asynchronous callback to a  `Disposable` that may be disposed before the callback is executed.
In such a case, the best strategy is usually to do nothing and return early.

> **Warning** Non-disposed objects shouldn't hold onto references to disposed objects, as this constitutes a memory leak. Once a `Disposable` is released, it should be completely inactive, and there's no reason to refer to it anymore.

### Ending a Disposable Lifecycle
A plugin can manually end a `Disposable` lifecycle by calling `Disposer.dispose(Disposable)`. 
This method handles recursively disposing of all the `Disposable` child descendants as well.

## The Disposable Interface
The IntelliJ Platform provides [`Disposable`](upsource:///platform/util/src/com/intellij/openapi/Disposable.java) as a way to manually manage the life of a subsystem's resources such as: 
* User Interface components, 
* File handles, and database connections, 
* Caches and other significant data structures, 
* Listener/subscriber patterns. 

### Disposable Implementation
Creating a class requires implementing the `Disposable` interface and defining the `dispose()` method.
An example is shown below:

```java
  public class Foo<T> extends JBFoo implements Disposable {
      public Foo(@NotNull Project project, @NotNull String name, @Nullable FileEditor fileEditor, @NotNull Disposable parentDisposable) {
        this(project, name, fileEditor, InitParams.createParams(project), DetachedToolWindowManager.getInstance(project));   
        Disposer.register(parentDisposable, this);
      }

     @Override
     public void dispose() {
       myFooManager.unregister(this);
       myDetachedToolWindowManager.unregister(myFileEditor);
       KeyboardFocusManager.getCurrentKeyboardFocusManager().removePropertyChangeListener("focusOwner", myMyPropertyChangeListener);
       setToolContext(null);
     }
  }
```

A lot of code setting-up all the conditions requiring release in `dispose()` has been omitted for simplicity.
Regardless, it illustrates the basic pattern, which is:
* In this case, the parent disposable is passed into the constructor,
* The `Foo` disposable is registered as a child of `parentDisposable` in the constructor.
* The `dispose()` method consolidates the necessary release actions and will be called by the `Disposer`.

> **Warning** Never call `Disposable.dispose()` directly because it bypasses the parent-child relationships established in `Disposer`. Always call `Disposer.dispose(Disposable)` instead. 

<br>
<br>
> **Note** **-- Editorial -- The following content is from the Google document "disposables.md." I assume the content is useful in documenting a choice of parent disposable other than** `Application`, `Project`, or `Module`. 
**I have cleaned up some style and format issues and removed a few Google-specific references.**


## Background
Objects implementing `Disposable` are connected based on a parent-child relationship so that when the parent gets disposed, all of its children also get recursively disposed.
This relationship allows a great extent of freedom and determinism in an object's lifetime control, but also means that if the parent instance is chosen unwisely, the child may consume resources for a longer period of time than is required. 
Continuing to use resources when they are no longer needed can be a severe source of contention due to leaving some zombie objects behind as a result of each invocation.
An additional challenge is that these kinds of issues won't be reported by the regular leak checker utilities, because technically, it's not a memory leak from the test suite perspective.

This section focuses on the aspect of parent lifetime and dives into commonly used contexts where this is likely to be a problem, what are the implications, fixes, pitfalls, etc.

### Message Bus Connection Example
Consider the following snippet subscribing to project-level events:

```java
  project.getMessageBus().connect(project).subscribe(_TOPIC_, new SomeListenerInterface() {...});
```

This snippet is a typical way of creating subscriptions to various IDE model events in the IntelliJ Platform. 
It probably looks familiar to many, but the truth is that it's not always innocent.

Here is a short recap of what happens above:
- `project.getMessageBus().connect(disposable)` creates a connection object whose lifetime is bound to that of the
  passed disposable instance.
- Because `Project` implements `Disposable` and is the "easiest" object to access almost everywhere, there are ~100 occurrences of this pattern found just by performing a full-text search on `.connect(project`, `.connect(myProject` etc. 
- The listener, being an anonymous or inner class, holds an implicit reference to the enclosing instance. 
  Since the listener belongs to the connection, the enclosing instance lifetime is bound to the lifetime of the passed `Disposable`, in this case, `Project`.

So virtually every object subscribed in such a way remains in memory as long as the entire `Project` instance is alive. 
While this choice of lifetime is valid in some cases - like project-level IntelliJ Platform services - it can be highly suboptimal when the enclosing instance is relatively heavyweight (as is often the case), and could be disposed before the `Project` instance is released.

### How Parent Subscriptions May Cause Problems
The [Message Bus Connection Example](#message-bus-connection-example) is not valid for temporary UI components or their children. 
They are often subscribed to IDE events in their constructors to update UI when, e.g., VFS roots change, or the IDE enters dumb mode. 
Once the UI component is no longer needed, it may remain in memory due to the implicit reference to the enclosing instance during connection, and it may even continue to process the model events by spending CPU cycles needlessly.

There are also examples of window content being cleared with `dispose=true`, which did not, in fact, release the UI components as expected.

<br>
  
> **Note** **-- Editorial -- The Google doc's "Choosing a Disposable Parent" section moved from here to this document's [Choosing a Disposable Parent](#choosing-a-disposable-parent) section above.**   

<br>

### Pitfalls
Although a `Project` instance is typically the longest living, it still gets disposed at some point.
In those situations, a plugin's project-related objects shouldn't be alive: neither the children nor the carefully chosen parent. 
Therefore, it's not enough to establish a parent-child relationship between two `Disposable` objects.
It is a best practice to register the parent against the project instance, or another parent with an appropriate lifetime, as shown in the snippet below:

```java
  Disposer.register(project, parent);
```

In this way, the child is guaranteed to be disposed: either when the parent is disposed explicitly via `Disposer.dispose(parent)`, or when the project is no longer alive - whichever comes first.

Imagine a notification panel `Disposable` registered with the `Disposer` using the project instance.  
The MessageBus connection created for the panel is registered with the `Disposer` using the panel instance as the parent.
A notification panel is disposed explicitly when a new one is ready. 
With these registrations in place the panel is guaranteed not to exceed its necessary lifetime:

```java
  Disposer.register(project, panelDisposable);
  Disposer.register(panelDisposable, messageBusDisposable);
```

However, omitting the panel's registration against the project creates an alternative "parent" root in the `Disposer` tree of `Disposables`.
If the alternative parent has a mismatched lifetime relative to the project, but the child holds a reference to the project because of the subscription code, this will lead to a project instance leak.   
