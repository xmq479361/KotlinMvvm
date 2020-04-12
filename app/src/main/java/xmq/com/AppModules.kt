package xmq.com

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xmq.base.net.apiModule
import xmq.base.net.ServerFactory
import xmq.com.api.WanApi
import xmq.com.resp.ArticleRemoteDataSource
import xmq.com.resp.ArticleRepository
import xmq.com.ui.home.BannerViewModel
import xmq.com.ui.home.HomeViewModel

val wandModule = module {
    //    single { get(ServerFactory::class, StringQualifier(), ).create(WanApi::class.java) }
//    single {ServerFactory(WanApi.BASE_URL).create(WanApi::class.java)} bind(WanApi::class);
    single { ServerFactory(WanApi.BASE_URL).create(WanApi::class.java) }
//    val simpleViewModel: SimpleViewModel by viewModel(clazz = SimpleViewModel::class) { parametersOf(DEFAULT_ID) }
    single(override = true) {
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        ).build()
    }
    single { ArticleRemoteDataSource(get()) }
    single { ArticleRepository(get()) }
    viewModel() { HomeViewModel(get()) }
    viewModel() { BannerViewModel(get()) }
}
val appModules = listOf(
    apiModule,
    wandModule
//    single<WanApi> { get<ServerFactory>().create(WanApi::class.java) }
//    single { ArticleRepository(get()) }
//    viewModel() { HomeViewModel(get()) }
)