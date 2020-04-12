package xmq.com.ui.home

//import xmq.basic.mvvm.BaseVMFragment
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import xmq.base.ext.logi
import xmq.base.ext.logw
import xmq.base.view.mvvm.BaseVMFragment
import xmq.base.widgets.SpaceItemDecoration
import xmq.com.R
import xmq.com.adapter.ArticleAdapter
import xmq.com.module.GlideImageLoader
import xmq.ext.android.dp2px
import xmq.ext.android.observe
import xmq.ext.android.toastShort


class HomeFragment : BaseVMFragment<HomeViewModel>(R.layout.fragment_home) {
    override val mViewModel: HomeViewModel by viewModel()
    val mBannerViewModel: BannerViewModel by viewModel()
    val mAdapter by lazy { ArticleAdapter() }
    private val mBanner by lazy { Banner(activity) }
    private val mBannerTitles = mutableListOf<String>()
    private val mBannerImages = mutableListOf<String>()
    private val mBannerUrls = mutableListOf<String>()
    override fun startObserve(viewModel: HomeViewModel) {
        logi("startObserve")
        observe(mBannerViewModel.mBanner) {
            if (it.isEmpty()) return@observe
            mBannerTitles.clear()
            mBannerImages.clear()
            mBannerUrls.clear()
            it.forEach {
                mBannerTitles.add(it.title)
                mBannerImages.add(it.imagePath)
                mBannerUrls.add(it.url)
            }
            mBanner.setImages(mBannerImages)
                .setBannerTitles(mBannerTitles)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setDelayTime(3000)
                .start()
        }
        observe(viewModel.mUIModel) {
            logi("viewModel.mArticles Observer ${it.showSuccess?.size}")
            val isCurrLoadMore = mAdapter.loadMoreModule.isLoading;
            logw("viewModel isLoadMore ${it.isLoadMore}")
            mAdapter.loadMoreModule.loadMoreComplete()
//            if (!it.showEnd)
            mAdapter.loadMoreModule.isEnableLoadMore = it.showEnd
//                if (!it.isLoadMore)
////                    mAdapter.loadMoreModule.loadMoreToLoading()
////                else {
//                }
            homeRefreshLayout.isRefreshing = it.isRefresh
            it.showError?.toastShort(context!!)
            if (it.showSuccess != null) {
                if (isCurrLoadMore) mAdapter.addData(it.showSuccess!!)
                else mAdapter.setNewInstance(it.showSuccess)
            };
        }
    }

    override fun onStart() {
        super.onStart()
        mBanner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        mBanner.stopAutoPlay()
    }

    override fun initView() {
        super.initView()
        with(mBanner) {
            layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(200))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener { }
        }
        homeRefreshLayout.apply {
            setOnRefreshListener { mViewModel.doRefresh() }
        }
        homeRecycleView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpaceItemDecoration(8))
            adapter = mAdapter.apply {
                if (headerLayoutCount > 0) removeAllHeaderView()
                addHeaderView(mBanner)
                loadMoreModule.setOnLoadMoreListener { mViewModel.doLoadMore() }
            }
        }
    }

    override fun initData(bundle: Bundle?) {
        super.initData(bundle)
        mBannerViewModel.doRefresh()
        mViewModel.doRefresh()
    }
}