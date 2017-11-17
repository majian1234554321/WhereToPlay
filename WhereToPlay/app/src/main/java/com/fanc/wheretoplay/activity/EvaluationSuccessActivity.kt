package com.fanc.wheretoplay.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fanc.wheretoplay.MainActivity
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.EvaluationSuccessAdapter
import com.fanc.wheretoplay.base.BaseActivity

import kotlinx.android.synthetic.main.activity_evaluation_success.*

class EvaluationSuccessActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.menu_iv_lefticon -> {
                finish()
            }
            R.id.tv_back -> {
                val intent = intent
                intent.setClass(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var list = ArrayList<String>()
        list.add("'1")
        list.add("'2")
        list.add("'3")
        setContentView(R.layout.activity_evaluation_success)
        tv_title.text = "评价成功"

        val evaluationSuccessAdapter = EvaluationSuccessAdapter(list)
        recycle.adapter = evaluationSuccessAdapter
        recycle.layoutManager = LinearLayoutManager(this)
        menu_iv_lefticon.setOnClickListener(this)

    }


}
