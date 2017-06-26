package com.sitexa.gankot.ui.fragment2

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sitexa.gankot.R
import com.sitexa.gankot.net.SweetApi
import com.sitexa.gankot.repository.Sweet
import com.sitexa.gankot.ui.activity.MainActivity
import com.sitexa.gankot.ui.adapter.MainAdapter
import com.sitexa.gankot.utils.dismissProgress
import com.sitexa.gankot.utils.showProgress
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sweet_container.*
import java.util.*

/**
 * xnpeng (xnpeng@hotmail.com)
 */
class SweetContainerFragment : Fragment() {

    var published: String? = null
    var sweetList:List<Sweet> = emptyList()
    var activity: MainActivity? = null

    companion object {
        fun newInstance(): SweetContainerFragment {
            return SweetContainerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_sweet_container, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        this.activity = activity as MainActivity?

    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun loadData() {
        showProgress()
        val api = SweetApi.create()
        api.latestSweet(10,1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    sweetList = result
                }, {}, { onComplete() })
    }

    private fun onComplete() {

        dismissProgress()
        setUpView()
    }

    private fun showProgress() {
        if (activity != null) {
            activity!!.showProgress()
        }
    }

    private fun dismissProgress() {
        if (activity != null) {
            activity!!.dismissProgress()
        }
    }

    private fun setUpView() {
        val fragments = ArrayList<Fragment>()

        //fragments.add(RecommendFragment.newInstance(published!!))
        //fragments.add(AndroidFragment.newInstance())
        //fragments.add(IOSFragment.newInstance())
        //fragments.add(WebFragment.newInstance())
        //fragments.add(VideoFragment.newInstance())
        //fragments.add(ExpandFragment.newInstance())
        //fragments.add(NewsFragment.newInstance())
        //fragments.add(NoticeFragment.newInstance())

        val titles = resources.getStringArray(R.array.sweet)
        viewPager.adapter = MainAdapter(fragments, titles, childFragmentManager)
        viewPager.offscreenPageLimit = 6

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = MODE_SCROLLABLE

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab!!.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

}