---
title: Messaging Infrastructure
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Purpose

The purpose of this document is to introduce the messaging infrastructure available in the IntelliJ Platform to developers and plugin writers. It is intended to answer why, when and how to use it.

## Rationale

So, what is messaging in the IntelliJ Platform and why do we need it? Basically, its implementation of
[Observer pattern](https://en.wikipedia.org/wiki/Observer_pattern)
that provides additional features like _broadcasting on hierarchy_ and special _nested events_ processing (_nested event_ here is a situation when new event is fired (directly or indirectly) from the callback of another event).

## Design

Here are the main components of the messaging API.

### Topic

This class serves as an endpoint at the messaging infrastructure. I.e. clients are allowed to subscribe to the topic within particular bus and to send messages to particular topic within particular bus.

![Topic](img/topic.svg)

*  *display name*  just a human-readable name used for logging/monitoring purposes;
*  *broadcast direction*  will be explained in details at Broadcasting. Default value is *TO\_CHILDREN*;
*  *listener class*  that is a business interface for particular topic.
Subscribers register implementation of this interface at the messaging infrastructure and publishers may later retrieve object that conforms (IS-A) to it and call any method defined there. Messaging infrastructure takes care on dispatching that to all subscribers of the topic, i.e. the same method with the same arguments will be called on the registered callbacks;

### Message Bus

Is the core of the messaging system. Is used at the following scenarios:

![Bus](img/bus.png)

### Connection

Manages all subscriptions for particular client within particular bus.

![Connection](img/connection.svg)

*  keeps number of *topic handler* mappings (callbacks to invoke when message for the target topic is received)
*Note*: not more than one handler per-topic within the same connection is allowed;

*  it's possible to specify *default handler* and subscribe to the target topic without explicitly provided callback.
Connection will use that *default handler* when storing *(topic-handler)* mapping;

*  it's possible to explicitly release acquired resources (*disconnect()* method).
Also it can be plugged to standard semi-automatic disposing 
(
[`Disposable`](upsource:///platform/util/src/com/intellij/openapi/Disposable.java)
);

### Putting Altogether

*Defining business interface and topic*

```java
public interface ChangeActionNotifier {

    Topic<ChangeActionNotifier> CHANGE_ACTION_TOPIC = Topic.create("custom name", ChangeActionNotifier.class)

    void beforeAction(Context context);
    void afterAction(Context context);
}
```

*Subscribing*

![Subscribing](img/subscribe.svg)

> **NOTE** If targeting 2019.3 or later, use [declarative registration](/basics/plugin_structure/plugin_listeners.md) if possible.


```java
public void init(MessageBus bus) {
    bus.connect().subscribe(ActionTopics.CHANGE_ACTION_TOPIC, new ChangeActionNotifier() {
        @Override
        public void beforeAction(Context context) {
            // Process 'before action' event.
        }
        @Override
        public void afterAction(Context context) {
            // Process 'after action' event.
        }
    });
}
```

*Publishing*

![Publishing](img/publish.svg)

```java
public void doChange(Context context) {
    ChangeActionNotifier publisher = myBus.syncPublisher(ActionTopics.CHANGE_ACTION_TOPIC);
    publisher.beforeAction(context);
    try {
        // Do action
        // ...
    } finally {
        publisher.afterAction(context)
    }
}
```

*Existing resources*

*  *MessageBus* instances are available via
[`ComponentManager.getMessageBus()`](upsource:///platform/extensions/src/com/intellij/openapi/components/ComponentManager.java)
(many standard interfaces implement it, e.g.
[`Application`](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java),
[`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java);

*  number of public topics are used by the *IntelliJ Platform*, e.g.
[`AppTopics`](upsource:///platform/platform-api/src/com/intellij/AppTopics.java),
[`ProjectTopics`](upsource:///platform/projectModel-api/src/com/intellij/ProjectTopics.java)
etc.
So, it's possible to subscribe to them in order to receive information about the processing;

## Broadcasting

Message buses can be organised into hierarchies. Moreover, the *IntelliJ Platform* has them already:

![Standard hierarchy](img/standard_hierarchy.svg)

That allows to notify subscribers registered in one message bus on messages sent to another message bus.

*Example:*

![Parent-child broadcast](img/parent_child_broadcast.svg)

Here we have a simple hierarchy (*application bus* is a parent of *project bus*) with three subscribers for the same topic.

We get the following if *topic1* defines broadcast direction as *TO\_CHILDREN*:
1.  A message is sent to *topic1* via *application bus*;
2.  *handler1* is notified about the message;
3.  The message is delivered to the subscribers of the same topic within *project bus* (*handler2* and *handler3*);

*Benefits*

We don't need to bother with memory management of subscribers that are bound to child buses but interested in parent bus-level events.

Consider the example above we may want to have project-specific functionality that reacts to the application-level events. 
All we need to do is to subscribe to the target topic within the *project bus*.
No hard reference to the project-level subscriber will be stored at application-level then, 
i.e. we just avoided memory leak on project re-opening.

*Options*

Broadcast configuration is defined per-topic. Following options are available:

*  _TO\_CHILDREN_ (default);

*  _NONE_;

*  _TO\_PARENT_;

## Nested Messages

_Nested message_ is a message sent (directly or indirectly) during another message processing.
The IntelliJ Platform's Messaging infrastructure guarantees that all messages sent to particular topic will be delivered at the sending order.

*Example:*

Suppose we have the following configuration:

![Nested messages](img/nested_config.svg)

Let's see what happens if someone sends a message to the target topic:

*  _message1_ is sent;

*  _handler1_ receives _message1_ and sends _message2_ to the same topic;

*  _handler2_ receives _message1_;

*  _handler2_ receives _message2_;

*  _handler1_ receives _message2_;

## Tips'n'tricks

### Relief Listeners Management

Messaging infrastructure is very light-weight, so, it's possible to reuse it at local sub-systems in order to relief
[Observers](https://en.wikipedia.org/wiki/Observer_pattern) construction. Let's see what is necessary to do then:

1. Define business interface to work with;

2. Create shared message bus and topic that uses the interface above (_shared_ here means that either _subject_ or _observers_ know about them);

Let's compare that with a manual implementation:

1. Define listener interface (business interface);

2. Provide reference to the _subject_ to all interested listeners;

3. Add listeners storage and listeners management methods (add/remove) to the _subject_;

4. Manually iterate all listeners and call target callback in all places where new event is fired;

### Avoid Shared Data Modification from Subscribers

We had a problem in a situation when two subscribers tried to modify the same document
([IDEA-71701](https://youtrack.jetbrains.com/issue/IDEA-71701)).

The thing is that every document change is performed by the following scenario:

1. _before change_ event is sent to all document listeners and some of them publish new messages during that;

2.  actual change is performed;

3.  _after change_ event is sent to all document listeners;

We had the following then:

1.  _message1_ is sent to the topic with two subscribers;
2.  _message1_ is queued for both subscribers;
3.  _message1_ delivery starts;
4.  _subscriber1_ receives _message1_;
5.  _subscriber1_ issues document modification request at particular range (e.g. _document.delete(startOffset, endOffset)_);
6.  _before change_ notification is sent to the document listeners;
7.  _message2_ is sent by one of the standard document listeners to another topic within the same message bus during _before change_ processing;
8.  the bus tries to deliver all pending messages before queuing _message2_;
9.  _subscriber2_ receives _message1_ and also modifies a document;
10.  the call stack is unwinded and _actual change_ phase of document modification operation requested by _subscriber1_ begins;

**The problem**  is that document range used by _subscriber1_ for initial modification request is invalid if _subscriber2_ has changed document's range before it.


