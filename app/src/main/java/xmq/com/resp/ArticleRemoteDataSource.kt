package xmq.com.resp

import xmq.base.ext.apiCall
import xmq.base.reps.BaseRemoteDataSource
import xmq.basic.entity.MResponse
import xmq.com.api.WanApi
import xmq.com.model.Article
import xmq.com.model.Banner
import xmq.com.model.MPage

class ArticleRemoteDataSource(private val mServer: WanApi) : BaseRemoteDataSource() {
    suspend fun getBanner(): MResponse<List<Banner>> {
        return apiCall { mServer.getBanner() }
    }

    suspend fun getArticleList(page: Int = 0): MResponse<MPage<Article>> {
        return apiCall { mServer.getArticleList(page) }
    }
}