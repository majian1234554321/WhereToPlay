package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import kotlinx.android.synthetic.main.bgirlfragment.*

/**
 *
 * @author admin
 * @date 2018/3/15
 */
class BGirlFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv1 -> {
                val intent = Intent(mContext, DisplayActivity::class.java)
                intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment1")
                startActivity(intent)
            }
            R.id.tv2 -> {
            }
            R.id.tv3 -> {
            }
            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("娱乐行业从业申请")
        tv1.setOnClickListener(this)
        tv2.setOnClickListener(this)
        tv3.setOnClickListener(this)

    }

    companion object {
        fun newInstance(): BGirlFragment {
            val fragment = BGirlFragment()
            return fragment
        }
    }
}