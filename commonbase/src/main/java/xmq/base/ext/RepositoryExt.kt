package xmq.base.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import xmq.base.net.IResponse
import xmq.base.net.XResult
import java.io.IOException

suspend fun <T, R : IResponse<T>> ICoroutine.apiCall(block: suspend () -> R): R {
    return block.invoke()
}

suspend fun <T : Any, R : IResponse<T>> ICoroutine.apiCallSafe(
    block: suspend () -> XResult<T>,
    errorMsg: String
): XResult<T> {
    return try {
        block()
    } catch (e: Exception) {
        e.printStackTrace()
        XResult.Error(IOException(errorMsg));
    }
}

suspend fun <T : Any, R : IResponse<T>> ICoroutine.exec(
    response: R,
    successBlock: suspend CoroutineScope.() -> Unit,
    errorBlock: (suspend CoroutineScope.() -> Unit)? = null
): XResult<T> {
    return coroutineScope {
        "exec: ${response.isSuccess()}, ${response.exception()}"
        if (response.isSuccess()) {
            successBlock.let { it() }
            XResult.Success(response.data())
        } else {
            errorBlock?.let { it() }
            XResult.Error(response.exception())
        }
    }
}

/**

suspend fun <T : Any> execResponse(
response: MResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
errorBlock: (suspend CoroutineScope.() -> Unit)? = null
): MResult<T> {
return coroutineScope {
if (response.errorCode == -1) {
errorBlock?.let { it() }
MResult.Error(IOException(response.errorMsg))
} else {
successBlock?.let { it() }
MResult.Success(response.data)
}
}
}
}
 */