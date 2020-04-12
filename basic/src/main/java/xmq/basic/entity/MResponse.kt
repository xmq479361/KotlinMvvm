package xmq.basic.entity

import xmq.base.net.IResponse
import java.io.IOException

data class MResponse<out T : Any>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
) : IResponse<T> {
    override fun data(): T = data

    override fun isSuccess(): Boolean = errorCode == 0

    override fun exception(): Exception = IOException(errorMsg)

}