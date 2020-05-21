---
title: Disposer Memory Issues 
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **Note** **-- Editorial -- This page is from the Google document "Android Studio - Fixing Memory Issues.pdf." I have cleaned up some style and format issues and removed a few Google-specific references.**                       

## Introduction
When the application exits, it performs a final sanity check to verify everything was disposed. 
If something was registered with the `Disposer` but remains undisposed, the IntelliJ Platform reports it before shutting down.
See [The Disposer and Disposables](/reference_guide/disposer_guide.md) page for more information about [`Disposer`](upsource:///platform/util/src/com/intellij/openapi/util/Disposer.java) and [`Disposable`](upsource:///platform/util/src/com/intellij/openapi/Disposable.java).

## Tracking down a Memory Leak
In Debug mode, registering a `Disposable` with the `Disposer` also registers a stack trace for the object's allocation path.
The `Disposer` accomplishes this by creating a dummy `Throwable` at the time of registration.

The following snippet represents the sort of "memory leak detected" error encountered in practice:

```text
    Java.lang.RuntimeException:
    Memory leak detected: <Instance> of class <com.example.classtype>
    See the cause for the corresponding Disposer.register() stacktrace:
        at ObjectTree.assertIsEmpty(ObjectTree.java:209)
        at Disposer.assertIsEmpty(Disposer.java:125)
        at Disposer.assertIsEmpty(Disposer.java:121)
        at ApplicationImpl.disposeSelf(ApplicationImpl.java:323)
        at ApplicationImpl.doExit(ApplicationImpl.java:780)
        …
    Caused by: java.lang.Throwable
        at ObjectTree.getOrCreateNodeFor(ObjectTree.java:101)
        at ObjectTree.register(ObjectTree.java:62)
        at Disposer.register(Disposer.java:81)
        at Disposer.register(Disposer.java:75)
        …
        at ProjectManagerEx.createProject(ProjectManagerEx.java:69)
        at NewProjectWizardDynamic.doFinish(NewProjectWizardDynamic.java:235)
        at DynamicWizard$1.run(DynamicWizard.java:433)
        at CoreProgressManager$5.run(CoreProgressManager.java:237)
        at CoreProgressManager$TaskRunnable.run(CoreProgressManager.java:563)
        …
```

> **Tip** The first part of the callstack is unrelated to diagnosing the memory leak. Instead, pay attention to the second part of the call stack, after `Caused by: java.lang.Throwable`.

In this specific case, the IntelliJ Platform ([`CoreProgressManager`](upsource:///platform/core-impl/src/com/intellij/openapi/progress/impl/CoreProgressManager.java)) started a task that contained the `DynamicWizard` code.
That code, in turn, allocated a `Project` that was never disposed by the time the application exited. 
That is a promising initial place to start digging.

The above memory leak was ultimately caused by failing to pass a `Project` instance to a function responsible for registering it for disposal. 
Often the fix for a memory leak is as simple as understanding the memory scope of the object being allocated - often a UI container, project, or application - and making sure a `Disposer.register()` call is made appropriately for it.   