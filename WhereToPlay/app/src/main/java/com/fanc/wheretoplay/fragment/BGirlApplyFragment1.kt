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
import kotlinx.android.synthetic.main.bgirlapplyfragment1.*
import kotlinx.android.synthetic.main.bgirltitle.*

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment1 : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment1, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("从业申请")
        tv1_select.setTextColor(Color.parseColor("#c4483c"))
        next.setOnClickListener {
            if (checkbox.isChecked) {
                val intent = Intent(mContext, DisplayActivity::class.java)
                intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment2")
                intent.putExtra("bgirltype", arguments?.getString("bgirltype"))
                startActivity(intent)
            } else {
                Toast.makeText(mContext, "确认阅读相关须知", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        fun newInstance(bgirltype:String):BGirlApplyFragment1{
            val bGirlApplyFragment1 = BGirlApplyFragment1()


            val args = Bundle()

            args.putString("bgirltype", bgirltype)

            bGirlApplyFragment1.arguments = args





            return bGirlApplyFragment1
        }
    }
}