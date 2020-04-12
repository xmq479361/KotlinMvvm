package xmq.base.net;

import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Retrofit

class ServerFactory(url: String) : KoinComponent {
    val retrofit by inject<Retrofit> { parametersOf(url); }
    fun <T> create(clz: Class<T>): T {
        return retrofit.create(clz);
    }
}