package com.fanc.wheretoplay.fragment

import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
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


        //0未申请1未审核2已审核3审核不通过
        when (arguments?.getString("status")) {
            "1" -> {
                tv_text.text = "资料审核中，请耐心等待···"
                iv_status.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.iv_status1))
                next.visibility = View.GONE
            }
            "2" -> {
                tv_text.text = "恭喜，资料审核通过，请支付相应费用"
                iv_status.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.iv_status2))
                next.text = "支付"
                next.visibility = View.VISIBLE
                next.setOnClickListener {
                    val intent = Intent(mContext, DisplayActivity::class.java)
                    intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment4")
                    startActivity(intent)
                }

            }
            "3" -> {
                tv_text.text = "资料审核失败，请重新提交信息登记"
                iv_status.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.iv_status3))
                next.text = "登记"
                next.visibility = View.VISIBLE
                next.setOnClickListener {
                    val intent = Intent(mContext, DisplayActivity::class.java)
                    intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment2")
                    startActivity(intent)
                }
            }
            else -> {
            }
        }


    }

    companion object {
        fun newInstance(status: String): BGirlApplyFragment3 {
            val bGirlApplyFragment3 = BGirlApplyFragment3()
            val args = Bundle()
            args.putString("status", status)
            bGirlApplyFragment3.arguments = args

            return bGirlApplyFragment3
        }
    }
}