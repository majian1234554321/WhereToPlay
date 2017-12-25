package com.fanc.wheretoplay.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseFragment

import com.fanc.wheretoplay.util.UIUtils
import kotlinx.android.synthetic.main.fragment_about_us.*

/**
 *
 * @author Administrator
 * @date 2017/6/19
 */

class AboutUsFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return View.inflate(inflater.context, R.layout.fragment_about_us, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tm_about_us.setLeftIcon(R.drawable.left)
        tm_about_us.setTitle(R.string.about_us)
        tm_about_us.setTitleColor(resources.getColor(R.color.white))
        tm_about_us.setLeftIconOnClickListener {
            mContext.finish()
        }
        iv_about_us_version.setIcon(R.drawable.version)
        iv_about_us_version.setText(R.string.current_version)
        iv_about_us_version.setTextColor(mContext.resources.getColor(R.color.ddialog_text_color))
        iv_about_us_version.setRightTextColor(mContext.resources.getColor(R.color.ddialog_text_color))
        iv_about_us_version.setBackgroundColor(mContext.resources.getColor(R.color.about_us_version))
        iv_about_us_version.isShowLine(false)

        iv_about_us_version.rightText = "v" + UIUtils.getAppVersionName()
    }


}
