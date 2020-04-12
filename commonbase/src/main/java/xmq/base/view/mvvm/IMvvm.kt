package xmq.base.view.mvvm

import androidx.lifecycle.ViewModel

interface IMvvm<VM: ViewModel> {
    val mViewModel :VM
    fun startObserve(viewModel: VM)
}