package xmq.com.ui.home

import androidx.lifecycle.MutableLiveData
import xmq.base.ext.exec
import xmq.base.view.mvvm.BaseViewModel
import xmq.base.view.mvvm.launch
//import xmq.basic.mvvm.BaseViewModel
import xmq.basic.entity.MResponse
import xmq.base.ext.logi
import xmq.com.model.Banner
import xmq.com.resp.ArticleRepository

class BannerViewModel(mRepository: ArticleRepository) :
    BaseViewModel<ArticleRepository>(mRepository) {
    val mBanner = MutableLiveData<List<Banner>>()

    fun doRefresh() {
        logi("doRefresh")
        launch {
            val response: MResponse<List<Banner>> = repository.getBanner()
            exec(response, { mBanner.value = response.data() })
        }
    }

}