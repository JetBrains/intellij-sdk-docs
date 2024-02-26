<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Launching Coroutines

<link-summary>Techniques for launching coroutines from various contexts.</link-summary>

<include from="coroutines_snippets.md" element-id="learnCoroutines"/>

There are two approaches to launching coroutines in the IntelliJ Platform:
1. [Service with its own scope](#launching-coroutine-from-service-scope). (recommended)
2. [The `runBlockingCancellable` function](#using-runblockingcancellable).

## Launching Coroutine From Service Scope

The recommended approach is creating a [service](plugin_services.md) that receives [its scope](coroutine-scopes.md#service-scopes) via the constructor injection and launching a coroutine from the service methods.
Please note that while creating a service instance does allocate additional resources, using a dedicated service and scope remains a lightweight and, most importantly, safe solution for launching coroutines.
It should be used whenever possible.

The pattern is as follows:

<tabs>
<tab title="Application Service">

```kotlin
@Service
class MyApplicationService(
  private val cs: CoroutineScope
) {
  fun scheduleSomething() {
    cs.launch {
      // do something
    }
  }
}
```

</tab>
<tab title="Project Service">

```kotlin
@Service(Service.Level.PROJECT)
class MyProjectService(
  private val project: Project,
  private val cs: CoroutineScope
) {
  fun scheduleSomething() {
    cs.launch {
      // do something
    }
  }
}
```

</tab>
</tabs>

The injected scope is created per service, so each instance has its own isolated scope with a common parent, which is an [intersection scope](coroutine-scopes.md#intersection-scopes).
The injected scope is canceled when the container (application/project) is shut down or when the plugin is unloaded.

### Example Use Cases

#### Perform a Slow I/O Operation and Finish on UI Thread

The example below reads file content and displays a [notification](notifications.md#balloons) in the UI thread:

```kotlin
internal class DoLongAction : AnAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project ?: return
    project.service<LongOperationService>().startLongOperation()
  }
}

@Service(Service.Level.PROJECT)
class LongOperationService(
  private val project: Project,
  private val cs: CoroutineScope
) {
  fun startLongOperation() {
    cs.launch {
      val content = withBackgroundProgress(project, "Reading file") {
        // switch to IO dispatcher to read file data:
        withContext(Dispatchers.IO) {
          Files.readAllBytes(Path.of("~/example.txt"))
        }
      }
      // switch to EDT dispatcher to perform UI operation:
      withContext(Dispatchers.EDT) {
        Notification(
            "MyPlugin",
            "File content: $content",
            NotificationType.INFORMATION
        ).notify(project)
      }
    }
  }
}
```

#### Run Read Action and Finish on UI Thread

The example below starts a read action to retrieve required data from the [PSI model](psi.md) and renames a file, wrapping it in a command action:

```kotlin
class ReadPsiAction : AnAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project ?: return
    val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
    project.service<ReadPsiActionService>().startChange(psiFile)
  }
}

@Service(Service.Level.PROJECT)
class ReadPsiActionService(
  private val project: Project,
  private val cs: CoroutineScope
) {
  fun startChange(file: PsiFile) {
    cs.launch {
      // called on Default dispatcher:
      val count = readAction {
        if (!file.isValid) return@readAction -1
        file.viewProvider.allFiles.size
      }
      // switch to EDT dispatcher to perform UI operations:
      withContext(Dispatchers.EDT) {
        WriteCommandAction.writeCommandAction(project)
          .withName("Change it")
          .run<Throwable> {
            file.setName("renamed $count.txt")
          }
      }
    }
  }
}
```

#### Read PSI Data Under Read Lock and Rename File Under Write Lock

The example below runs a read action to compute a new file name and finishes by renaming a file under write lock:

```kotlin
internal class DangerousPsiAction : AnAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val project = e.project ?: return
    val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
    project.service<DangerousPsiActionService>().startChange(psiFile)
  }
}

@Service(Service.Level.PROJECT)
class DangerousPsiActionService(
  private val project: Project,
  private val cs: CoroutineScope
) {
  fun startChange(file: PsiFile) {
    cs.launch {
      // start a read action...
      readAndWriteAction {
        if (!file.isValid) return@readAndWriteAction value(Unit)
        val newFileName = computeNewName(file)
        // ...and finish with executing code under write lock:
        writeAction {
          file.setName(newFileName)
          Unit
        }
      }
    }
  }

  private fun computeNewName(file: PsiFile): String {
    // ...
  }
}
```

#### Launching Coroutines in UI Component

The code below presents an example UI component, which loads its data by calling a slow suspending method.

```kotlin
class SlowDataPanel(
  private val project: Project,
  parentDisposable: Disposable
) : JBLoadingPanel(BorderLayout(), parentDisposable) {
  // store a UI-dedicated child scope
  private val uiScope: CoroutineScope =
      project.service<UserDataService>().childScope("SlowDataPanel")

  init {
    // ...and cancel it when the UI is disposed:
    Disposer.register(parentDisposable) {
      uiScope.cancel()
    }
  }

  fun start() {
    setLoadingText("Loading user data...")
    startLoading()
    // load user data on IO dispatcher:
    uiScope.launch(Dispatchers.IO) {
      val user = project.service<UserDataService>().loadUserData()
      // ...and display it in the UI on EDT dispatcher:
      withContext(Dispatchers.EDT) {
        stopLoading()
        add(JLabel(user))
      }
    }
  }
}

@Service(Service.Level.PROJECT)
class UserDataService(
  private val project: Project,
  private val cs: CoroutineScope
) {
  fun childScope(name: String): CoroutineScope {
    return cs.namedChildScope(name)
  }

  suspend fun loadUserData(): String {
    // slow operation...
  }
}
```

In this case, a coroutine is not launched from the [service scope](coroutine-scopes.md#service-scopes), but launched from the UI component in its own scope, which is a child of the service scope.
This pattern still limits the launched coroutine to the service scope and follows the recommended approach.

## Using `runBlockingCancellable`

> Using `runBlockingCancellable` is not recommended.
> Use [service scopes](#launching-coroutine-from-service-scope) whenever possible.
>
{style="warning"}

In a standard coroutine-based application, the bridge between the regular blocking code and the suspending code is the [`runBlocking`](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html) function.

In the IntelliJ Platform, a similar purpose is achieved by the [`runBlockingCancellable`](%gh-ic%/platform/core-api/src/com/intellij/openapi/progress/coroutines.kt) function.
In addition to the same semantics as `runBlocking`, the action gets canceled when the current progress indicator or the current job is canceled.
