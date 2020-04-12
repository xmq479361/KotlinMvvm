package xmq.base.net

import java.lang.Exception

sealed class XResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : XResult<T>()
    data class Error(val exception: Exception): XResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
