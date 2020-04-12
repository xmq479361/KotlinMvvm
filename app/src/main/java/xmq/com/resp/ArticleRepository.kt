package xmq.com.resp

import xmq.basic.entity.MResponse
import xmq.base.reps.BaseRepositoryRemote
import xmq.com.model.Article
import xmq.com.model.Banner
import xmq.com.model.MPage

class ArticleRepository(remote: ArticleRemoteDataSource):
    BaseRepositoryRemote<ArticleRemoteDataSource>(remote) {

    suspend fun getBanner(): MResponse<List<Banner>> = remote.getBanner();

    /**
     * 首页文章列表
     */
    suspend fun getArticleList(page: Int = 0): MResponse<MPage<Article>> {
        return remote.getArticleList(page)
    }

}