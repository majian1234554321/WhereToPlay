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



/**
 *
 * @author admin
 * @date 2017/12/14
 */
class CouponFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(inflater.context, R.layout.couponfragment, null)
        view.findViewById<TitleBarView>(R.id.tbv).setTv_title("优惠券")

        loadData()

        val list = arrayListOf<BaseLazyFragment>()
        list.add(CouponChild1Fragment())
        list.add(CouponChild2Fragment())
        list.add(CouponChild3Fragment())


        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = CouponFragmentAdapter(list)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)



        return view
    }

    private fun loadData() {
        //tab_layout
    }

    inner class CouponFragmentAdapter(val list: ArrayList<BaseLazyFragment>) : FragmentPagerAdapter(fragmentManager) {


        var stringArray: Array<String> = arrayOf("未使用", "使用记录", "已过期")

        override fun getItem(position: Int): Fragment = list[position]

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? = stringArray[position]

    }


}