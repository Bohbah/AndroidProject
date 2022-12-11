package com.androidstudioprojects.grapevine

import android.R.attr.author
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseUser
import java.text.ParseException


class ChatAdapter(val context: Context, val messages: List<Message>)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_incoming, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        val mMessage = messages[position]
        holder.bind(mMessage)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView
        val tvBody: TextView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvBody = itemView.findViewById(R.id.tvBody)
        }

        fun bind(mMessage: Message) {
            tvBody.text = mMessage.getContent()
        }
    }
}