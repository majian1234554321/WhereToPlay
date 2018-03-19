package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import kotlinx.android.synthetic.main.bgirlapplyfragment4.*
import kotlinx.android.synthetic.main.bgirltitle.*

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment4 : BaseFragment(), RadioGroup.OnCheckedChangeListener {

    var payValue = 1

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_wxpay -> {
                payValue = 1
                Toast.makeText(mContext, "1", Toast.LENGTH_SHORT).show()
            }

            R.id.rb_alipay -> {
                payValue = 2
                Toast.makeText(mContext, "2", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment4, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("从业申请")
        tv4.setTextColor(Color.parseColor("#c4483c"))
        next.setOnClickListener {
            val intent = Intent(mContext, DisplayActivity::class.java)
            intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment5")
            startActivity(intent)
        }

        rg.setOnCheckedChangeListener(this)

    }

    companion object {
        fun newInstance(): BGirlApplyFragment4 {
            return BGirlApplyFragment4()
        }
    }
}