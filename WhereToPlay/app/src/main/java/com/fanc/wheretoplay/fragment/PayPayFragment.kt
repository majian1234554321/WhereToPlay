package com.fanc.wheretoplay.fragment

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseFragment
import kotlinx.android.synthetic.main.paypayfragment.*

@SuppressLint("ValidFragment")
/**
 *
 * @author admin
 * @date 2017/12/24
 */
class PayPayFragment(val value0: String?, val value1: String?, val value2: String?, val value3: String?, val value4: String?, val value5: String?) : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.paypayfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv00.text = value0
        tv01.text = value1
        tv02.text = value2
        tv03.text = value3
        tv04.text = value4
        tv05.text = value5

        tv05.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG


    }
}