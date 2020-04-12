package xmq.base.view.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xmq.base.ext.tryCatch

fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit){
    viewModelScope.launch{
        block()
    }
}
suspend fun ViewModel.launchOnIO(block: suspend CoroutineScope.() -> Unit){
    withContext(Dispatchers.IO) {
        block()
    }
}

fun ViewModel.launchOnTryCatch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Exception) -> Unit,
        finallyBlock: (suspend CoroutineScope.() -> Unit),
        handleCancellationExceptionManually: Boolean
    ) {
        launch {
            tryCatch(tryBlock, catchBlock, finallyBlock, handleCancellationExceptionManually)
        }
    }
