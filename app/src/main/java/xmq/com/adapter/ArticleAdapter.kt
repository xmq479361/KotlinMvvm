package xmq.com.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import xmq.base.ext.logi
import xmq.com.R
import xmq.com.model.Article
//import xmq.util.SingletonHolder

class ArticleAdapter() :
    BaseQuickAdapter<Article, BaseViewHolder>(layoutResId = R.layout.item_article_constraint),
    LoadMoreModule {
    private val TAG: String = javaClass.simpleName
    override fun convert(holder: BaseViewHolder, item: Article) {
//        ArticleAdapter.instance()
        "convert:  ${item.title}".logi(TAG)
        with(item) {
            holder.setText(R.id.articleAuthor, author)
            holder.setText(R.id.articleTitle, title)
        }
    }
    companion object {
//        val instance = SingletonHolder<ArticleAdapter>(::ArticleAdapter)
    }
}