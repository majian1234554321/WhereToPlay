package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.MainActivity
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import kotlinx.android.synthetic.main.bgirlapplyfragment5.*
import kotlinx.android.synthetic.main.bgirlinfo.*
import kotlinx.android.synthetic.main.bgirltitle.*

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment5 : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment5, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("支付结果")
        tv5.setTextColor(Color.parseColor("#c4483c"))

        next.setOnClickListener {
            mContext.startActivity(Intent(mContext, MainActivity::class.java))
            mContext.finish()
        }


    }

    companion object {
        fun newInstance(): BGirlApplyFragment5 {
            return BGirlApplyFragment5()
        }
    }
}