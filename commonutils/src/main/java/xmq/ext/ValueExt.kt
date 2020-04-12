package xmq.ext


fun <T> Any?.notNull(f: () -> T, t: () -> T): T {
    return if (this != null) f() else t()
}


val Any.TAG: String
    get() = javaClass.simpleName