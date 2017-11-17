package com.fanc.wheretoplay.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R

import kotlinx.android.synthetic.main.evaluationsuccessadapter.view.*


/**
 * Created by admin on 2017/11/16.
 */

class EvaluationSuccessAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<EvaluationSuccessAdapter.ViewHolder>() {


    override fun getItemCount(): Int = if (list.size>0)list.size else 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(parent?.context, R.layout.evaluationsuccessadapter, null))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            tv_reserve_item_title.text = list[position]
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}
