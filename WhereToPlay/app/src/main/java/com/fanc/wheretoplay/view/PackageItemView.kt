package com.fanc.wheretoplay.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.fanc.wheretoplay.R
import kotlinx.android.synthetic.main.packageitemview.view.*

/**
 *
 * @author admin
 * @date 2017/12/23
 */
class PackageItemView : RelativeLayout {
    var tv_top: TextView? = null
    var tv_buttom: TextView? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        initView(context)
    }

    private fun initView(context: Context) {
        val view = View.inflate(context, R.layout.packageitemview, this)
        tv_top = view.findViewById<TextView>(R.id.tv_top) as TextView
        tv_buttom = view.findViewById<TextView>(R.id.tv_buttom) as TextView
    }

    fun setTopText(value: String) {
        tv_top?.text = value
    }

    fun setButtomText(value: String) {
        tv_buttom?.text ="●"+ value
    }

    fun getButtomText():String {
        return tv_buttom?.text.toString()
    }
}