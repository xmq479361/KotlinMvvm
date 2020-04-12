package xmq.base.view.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xmq.base.ext.ICoroutine
import xmq.base.reps.IRepository

open class BaseViewModel<out REPO : IRepository>(val repository: REPO) :
    ViewModel(), ICoroutine {
    val mException: MutableLiveData<Throwable> = MutableLiveData()

}