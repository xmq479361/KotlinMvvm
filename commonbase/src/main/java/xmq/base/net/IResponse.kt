package xmq.base.net


interface IResponse<out T : Any> {
    fun data(): T
    fun isSuccess(): Boolean
    fun exception(): Exception
}