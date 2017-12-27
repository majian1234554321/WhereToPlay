package com.fanc.wheretoplay.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R

import kotlinx.android.synthetic.main.packagedateadapter.view.*

/**
 *
 * @author admin
 * @date 2017/12/27
 */
class PackageDateAdapter(val context: Context, val list: List<String>) : RecyclerView.Adapter<PackageDateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
            = ViewHolder(View.inflate(context, R.layout.packagedateadapter, null))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        with(holder?.itemView!!) {
            tvs.text = list[position]
            //如果是00:00 显示角标
            if ("00:00" == list[position]) {

            } else {

            }

            holder?.itemView?.setOnClickListener { position ->

            }

        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}