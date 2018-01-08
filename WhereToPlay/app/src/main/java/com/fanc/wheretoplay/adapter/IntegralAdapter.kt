package com.fanc.wheretoplay.adapter


import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.datamodel.ScoreList
import com.fanc.wheretoplay.util.DateFormatUtil
import kotlinx.android.synthetic.main.item_integral_detail.view.*


/**
 * Created by Administrator on 2017/6/17.
 */

class IntegralAdapter(val context: Activity, val list: List<ScoreList.ContentBean.ListBean>) : RecyclerView.Adapter<IntegralAdapter.ViewHolder>() {


    override fun getItemCount(): Int = if (list.isNotEmpty()) list.size else 0

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            tv_time?.text = DateFormatUtil.stampToDate(list[position].finish_time)
            tv_item_integral_no.text = "+" + java.lang.Double.parseDouble(list[position].score_detail).toInt()+"积分"
            tv_item_integral_title.text = list[position].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(parent?.context, R.layout.item_integral_detail, null))


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}
