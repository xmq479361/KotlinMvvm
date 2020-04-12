package xmq.util

/**
 * Used to allow Singleton with arguments in Kotlin while keeping the code efficient and safe.
 *
 * See https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 */
open class SingletonHolder<out T>(private val creator: () -> T) {
    @Volatile
    private var instance: T? = null
    fun getInstance(): T =
            instance ?: synchronized(this) {
                instance ?: creator().apply {
                    instance = this
                }
            }
}