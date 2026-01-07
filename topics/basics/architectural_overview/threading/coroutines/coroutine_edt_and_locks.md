<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Coroutines on EDT and Locks

> This page is outdated and will be removed in the future.
>
> It contained information about planned changes in the threading model and adjustments required in plugins to work correctly.
>
> Since 2025.1, the problem has been solved internally in the IntelliJ Platform, and **no code adjustments are required in plugins**.
>
{title="Page Obsolesence Notice" style="warning"}

Sometimes, errors similar to the below can be observed in the IDE running in the [internal mode](enabling_internal.md):

```
Access is allowed with explicit read lock.
Now each coroutine scheduled on EDT wrapped in implicit write intent
lock (which implies read lock too). This implicit lock will be removed
in future releases.
Please, use explicit lock API like ReadAction.run(),
WriteIntentReadAction.run(), readAction() or writeIntentReadAction()
to wrap code which needs lock to access model or PSI.
Please note, that read action API can re-schedule your code
to background threads, if you are sure that your code need
to be executed on EDT, you need to use write intent read action.
```

This is not an actual error.
Nothing is broken and no data races occurred.

This assertion message was a way to prepare plugins for the changes in the IntelliJ Platform, but it is no longer necessary.
It is safe to ignore it.
