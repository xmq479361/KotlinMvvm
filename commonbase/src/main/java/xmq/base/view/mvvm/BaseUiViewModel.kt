package xmq.base.view.mvvm

import androidx.lifecycle.MutableLiveData
import xmq.base.ext.logd
import xmq.base.ext.toJson
import xmq.base.reps.IRepository
import xmq.base.view.model.IUiModel
import xmq.ext.TAG

abstract class BaseUiViewModel<out REPO : IRepository, MODEL : IUiModel>(repository: REPO) :
    BaseViewModel<REPO>(repository) {

    fun emitUiModel(uiModel: MODEL) {
//        val user = Gson().fromJson<User>(userJson, User::class.java)
        "UIModel: ${uiModel.toJson()}".logd(TAG);
        mUIModel.postValue(uiModel)
    }

    fun emitUiModel(builder: () -> MODEL) {
        mUIModel.postValue(builder())
    }

    val mUIModel: MutableLiveData<MODEL> = MutableLiveData()

}