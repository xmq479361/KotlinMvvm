package xmq.com.ui.home

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import xmq.base.ext.exec
import xmq.base.view.mvvm.BaseUiViewModel
import xmq.base.view.mvvm.launch
import xmq.basic.entity.MResponse
import xmq.base.ext.loge
import xmq.base.ext.logi
import xmq.com.model.Article
import xmq.com.model.MPage
import xmq.com.resp.ArticleRepository
import xmq.ext.TAG

class HomeViewModel(repository: ArticleRepository) :
    BaseUiViewModel<ArticleRepository, HomeUiModel>(repository) {
    val mArticles = MutableLiveData<MutableList<Article>>();

    //    val mArticles = MutableLiveData<MPage<Article>>();

    fun doRefresh() {
        logi("doRefresh")
        loadArticleByPage(0);
    }

    fun doLoadMore() {
        logi("doLoadMore ${mUIModel.value!!.pageNo}")
        loadArticleByPage(mUIModel.value!!.pageNo);
    }

    fun emit(builder: HomeUiModel.() -> Unit) {
        emitUiModel(HomeUiModel().apply(builder))
    }

    fun emitUiModel2(modelCreator: () -> HomeUiModel, builder: HomeUiModel.() -> Unit) {
        mUIModel.postValue(modelCreator().apply(builder))
    }

    private fun loadArticleByPage(page: Int) {
//        emit { isRefresh = true }
        launch {
            delay(1200)
            val response: MResponse<MPage<Article>> = repository.getArticleList(page);
            exec(response, {
                logi("loadArticleByPage: ${page}, size: ${response.data.datas.size}")
                response.data.let { mPage ->
                    emitUiModel(HomeUiModel().apply {
                        isRefresh = false
                        pageNo = mPage.curPage
                        pageTotal = mPage.pageCount
                        showSuccess = mPage.datas
                        showEnd = mPage.pageCount >= pageNo
                    });
                }
            }, {
                //                MainApp.INSTANCE.toast(response.errorMsg);
                response.errorMsg.loge(HomeViewModel@ this.TAG)
            })
//                "exec result: ${exec}".logi(HomeViewModel@this.TAG)
        }
    }
}