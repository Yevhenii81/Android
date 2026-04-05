package com.example.hw_03_04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

class ChatAdapter(private val chatList: List<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAvatar: TextView = itemView.findViewById(R.id.tv_avatar)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvMessage: TextView = itemView.findViewById(R.id.tv_message)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        val tvUnread: TextView = itemView.findViewById(R.id.tv_unread)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount() = chatList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]

        holder.tvName.text = chat.name
        holder.tvMessage.text = chat.message
        holder.tvTime.text = chat.time

        holder.tvAvatar.text = chat.name.first().toString().uppercase()

        if (chat.unread > 0) {
            holder.tvUnread.visibility = View.VISIBLE
            holder.tvUnread.text = chat.unread.toString()
        } else {
            holder.tvUnread.visibility = View.GONE
        }
    }
}