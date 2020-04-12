package xmq.base.view.model

open class BaseUiModel<T> (
    var showLoading: Boolean = false,
    var showError: String? = null,
    var showSuccess: T? = null
) : IUiModel
