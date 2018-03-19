package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import kotlinx.android.synthetic.main.bgirlapplyfragment3.*
import kotlinx.android.synthetic.main.bgirltitle.*

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment3 : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment3, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("从业申请")
        tv3.setTextColor(Color.parseColor("#c4483c"))
        next.setOnClickListener {
            val intent = Intent(mContext, DisplayActivity::class.java)
            intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment4")
            startActivity(intent)
        }

    }

    companion object {
        fun newInstance(): BGirlApplyFragment3 {
            return BGirlApplyFragment3()
        }
    }
}