package xmq.com.model
data class MPage<T>(
    val curPage: Int = 0,
    val datas: MutableList<T> = mutableListOf(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
)