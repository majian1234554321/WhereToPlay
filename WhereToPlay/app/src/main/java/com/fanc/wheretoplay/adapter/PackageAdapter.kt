package com.fanc.wheretoplay.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.datamodel.PackageModel
import kotlinx.android.synthetic.main.packageadapter.view.*

/**
 * @author admin
 * @date 2017/12/24
 */

class PackageAdapter(val context: Context, val product_list: List<PackageModel.ContentBean.ProductListBean>) : RecyclerView.Adapter<PackageAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            tv_001.text = product_list[position].name
            tv_002.text = product_list[position].price+"元"
            tv_003.text = product_list[position].num+product_list[position].unit

        }
    }

    override fun getItemCount(): Int = if (product_list.isNotEmpty()) product_list.size else 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder
            = ViewHolder(View.inflate(context, R.layout.packageadapter, null))

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}
