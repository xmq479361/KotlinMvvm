package xmq.base.view.model


open class PageUiModel<T>(
    showLoading: Boolean = false,
    showError: String? = null,
    showSuccess: T? = null,
    var pageNo: Int = 0,
    var pageTotal: Int = 0,
    var showEnd: Boolean = false, // 加载更多
    var isRefresh: Boolean = false, // 刷新
    var isLoadMore: Boolean = false // 刷新
) : BaseUiModel<T>(showLoading, showError, showSuccess)