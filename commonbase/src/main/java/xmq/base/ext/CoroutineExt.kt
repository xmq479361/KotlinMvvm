package xmq.base.ext

import kotlinx.coroutines.*

suspend fun CoroutineScope.tryCatch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: (suspend CoroutineScope.(Exception) -> Unit)? = null,
    finallyBlock: (suspend CoroutineScope.() -> Unit)? = null,
    handleCancellationExceptionManually: Boolean = false
) {
    try {
        tryBlock()
    } catch (e: Exception) {
        if (catchBlock != null && (e !is CancellationException || handleCancellationExceptionManually)) {
            catchBlock.let { it(e) }
        } else throw e
    } finally {
        finallyBlock?.let { it() }
    }
}

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
fun CoroutineScope.launchOnUI(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return this.launch(Dispatchers.Main) {
        block()
    }
}

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
fun CoroutineScope.launchOnIO(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return this.launch(Dispatchers.IO) {
        block()
    }
}

/**
 *  author:  HyJame
 *  date:    2020/3/11
 *  desc:    TODO
 */
fun CoroutineScope.launchOnUI(
    block: suspend CoroutineScope.() -> Unit,
    error: ((Throwable) -> Unit)? = null
    ): Job {
    return this.launch(Dispatchers.Main) {
        try {
            block()
        } catch (e: Exception) {
            error?.let { it(e) }
        }
    }
}
