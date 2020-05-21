---
title: Introduction to Disposer and Disposables
redirect_from:
  - /tutorials/disposables.html
  - /reference_guide/disposer.html
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Any collection of objects that keep expensive resources alive, and can be associated with some parent object's lifetime, should be registered with the IntelliJ Platform's `Disposer`. 

For more information, see:
* [Disposers and Disposables](/reference_guide/disposer_guide.md) for information about the `Disposer` and best practices for use of `Disposable`.
* [Disposer Memory Issues](/reference_guide/disposer_leaks.md) for information about debugging problems when using `Disposer`.
