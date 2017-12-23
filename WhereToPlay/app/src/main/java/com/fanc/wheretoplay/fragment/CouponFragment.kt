package com.fanc.wheretoplay.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.base.BaseLazyFragment
import com.fanc.wheretoplay.view.TitleBarView
import android.support.design.widget.TabLayout
import com.fanc.wheretoplay.datamodel.CouponCountModel
import com.fanc.wheretoplay.rx.ObservableOnSubscribe2
import com.fanc.wheretoplay.rx.ObservableTransformer2
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.MultipartBody


/**
 *
 * @author admin
 * @date 2017/12/14
 */
class CouponFragment : BaseFragment() {

    var tabLayout: TabLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(inflater.context, R.layout.couponfragment, null)
        view.findViewById<TitleBarView>(R.id.tbv).setTv_title("优惠券")
         tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)

        val list = arrayListOf<BaseLazyFragment>()
        list.add(CouponChild1Fragment())
        list.add(CouponChild2Fragment())
        list.add(CouponChild3Fragment())



        viewPager.adapter = CouponFragmentAdapter(list)

        tabLayout!!.setupWithViewPager(viewPager)




        loadData()
        return view
    }

    private fun loadData() {
        val requestFileA = MultipartBody.Part.createFormData("token", mUser.token)
       Retrofit_RequestUtils.getRequest().couponCount(requestFileA)
               .compose(ObservableTransformer2<CouponCountModel>())
               .subscribe(object : Observer<CouponCountModel> {
                   override fun onSubscribe(p0: Disposable) = Unit

                   override fun onError(p0: Throwable) {

                   }

                   override fun onNext(p0: CouponCountModel) {
                       tabLayout?.getTabAt(0)?.text = "未使用("+p0.content.list.available.toString()+")"
                       tabLayout?.getTabAt(1)?.text = "使用记录("+p0.content.list.used.toString()+")"
                       tabLayout?.getTabAt(2)?.text = "已过期("+p0.content.list.expired.toString()+")"
                   }

                   override fun onComplete() = Unit


               })



    }

    inner class CouponFragmentAdapter(val list: ArrayList<BaseLazyFragment>) : FragmentPagerAdapter(fragmentManager) {


        var stringArray: Array<String> = arrayOf("未使用", "使用记录", "已过期")

        override fun getItem(position: Int): Fragment = list[position]

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? = stringArray[position]

    }


}