package xmq.base.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {
    single {
//        get<OkHttpClient.Builder>().build()
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        ).build()
    }
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get());
    }
    single<Retrofit> { (url: String) ->
        get<Retrofit.Builder>()
            .baseUrl(url)
            .build();
    }

    single { (url: String) -> ServerFactory(url) }
}