package xmq.com.api

import retrofit2.http.GET
import retrofit2.http.Path
import xmq.basic.entity.MResponse
import xmq.com.model.Article
import xmq.com.model.Banner
import xmq.com.model.MPage

interface WanApi {
    companion object{
        val BASE_URL = "https://www.wanandroid.com"
    }
    @GET("/banner/json")
    suspend fun getBanner(): MResponse<List<Banner>>
    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int = 0): MResponse<MPage<Article>>

}