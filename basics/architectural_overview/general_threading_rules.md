---
title: General Threading Rules
---

In general, data structures in the *IntelliJ Platform* are covered by a single reader/writer lock.

Reading data is allowed from any thread.  Reading data from the UI thread does not require any special effort. However, read operations performed from any other thread need to be wrapped in a read action by using `ApplicationManager.getApplication().runReadAction()`.

Writing data is only allowed from the UI thread, and write operations always need to be wrapped in a write action with `ApplicationManager.getApplication().runWriteAction()`.

To pass control from a background thread to the event dispatch thread, instead of the standard `SwingUtilities.invokeLater()`, plugins should use `ApplicationManager.getApplication().invokeLater()`. The latter API allows specifying the _modality state_ for the call, i.e. the stack of modal dialogs under which the call is allowed to execute. Passing `ModalityState.NON_MODAL` means that the operation will be executed after all modal dialogs are closed. Passing `ModalityState.stateForComponent()` means that the operation may be executed while the specified component (part of a dialog) is still visible.
