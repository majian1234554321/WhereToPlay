package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.Display
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity

import com.fanc.wheretoplay.datamodel.MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean
import kotlinx.android.synthetic.main.extendadapter.view.*

/**
 * @author admin
 * @date 2017/12/24
 */

class ExtendAdapter(val context: Context, var flag: Boolean, val list: List<PkgListBean>) : RecyclerView.Adapter<ExtendAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(context, R.layout.extendadapter, null))


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            Glide.with(context).load(list[position].pic).placeholder(R.drawable.default_rect).into(iv)
            tv1text.text = list[position].name
            tv2.text = "￥" + list[position].discount_price
            tv3.text = "￥" + list[position].origin_price

            setOnClickListener {
                val intent = Intent(context, DisplayActivity::class.java)
                intent.putExtra("DISPLAYTYPE", "PackageFragment")
                intent.putExtra("idValue",list[position].id)
                context.startActivity(intent)

            }

        }
    }

    override fun getItemCount(): Int = if (flag) list.size else 2

    fun change(flag: Boolean) {
        this.flag = flag
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}
