package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.Display
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.activity.SignInActivity
import com.fanc.wheretoplay.datamodel.MerchantDetailModel

import com.fanc.wheretoplay.datamodel.MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.SPUtils
import kotlinx.android.synthetic.main.extendadapter.view.*

/**
 * @author admin
 * @date 2017/12/24
 */

class Extend0Adapter(val context: Context
) : RecyclerView.Adapter<Extend0Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(context, R.layout.extend0adapter, null))


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {


            }

        }


    override fun getItemCount(): Int {
       return 3
    }




    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}
