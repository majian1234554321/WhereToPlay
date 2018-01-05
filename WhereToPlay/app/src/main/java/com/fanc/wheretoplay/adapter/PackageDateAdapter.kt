package com.fanc.wheretoplay.adapter

import android.annotation.SuppressLint
import android.content.Context

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.fanc.wheretoplay.R
import android.support.design.widget.CoordinatorLayout.Behavior.setTag




/**
 *
 * @author admin
 * @date 2017/12/27
 */
class PackageDateAdapter(val context: Context, val list: List<String>, var positions: Int) : android.widget.BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int,   convertView: View?, p2: ViewGroup?): View {




        val view = View.inflate(context, R.layout.packagedateadapter, null)
        view.findViewById<TextView>(R.id.tvs).text = list[position]


        //如果是00:00 显示角标
        if ("00:00" == list[position]) {
            view.findViewById<ImageView>(R.id.ivs).visibility = View.VISIBLE
        } else {
            view.findViewById<ImageView>(R.id.ivs).visibility = View.GONE
        }
        if (positions == position) {
            view.findViewById<TextView>(R.id.tvs).setBackgroundResource(R.drawable.boder)
        } else {
            view.findViewById<TextView>(R.id.tvs).setBackgroundResource(R.drawable.boder2)
        }

        return view
    }

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size


    fun action(p2: Int) {
        positions = p2
        notifyDataSetChanged()
    }



}




