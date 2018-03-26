package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent

import android.support.v7.widget.RecyclerView

import android.view.View
import android.view.ViewGroup

import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DetailActivity

import com.fanc.wheretoplay.datamodel.MessageList

import com.fanc.wheretoplay.util.Constants


import kotlinx.android.synthetic.main.item_message.view.*



/**
 * Created by Administrator on 2017/6/16.
 */

class MessageAdapter(val context: Context, val messages: List<MessageList.Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.itemView) {
            tv_item_message_title?.text = messages[position].title
            tv_item_message_content.text = messages[position].content

            if ("0" == (messages[position].readed)) {
                v_unread.visibility = View.VISIBLE
            } else {
                v_unread.visibility = View.GONE
            }

            setOnClickListener {

                messages[position].readed = "-1"
                notifyDataSetChanged()

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(Constants.PAGE, Constants.MESSAGE_DETAIL)
                intent.putExtra(Constants.ID, messages[position].id)
                context.startActivity(intent)

            }

        }
    }

    override fun getItemCount(): Int = if (messages.isNotEmpty()) messages.size else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(context, R.layout.item_message, null))


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}
