package com.example.emoticare.data.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
//
//class ChatAdapter(private val messages: MutableList<ChatResponse>) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {
//
//    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_received, parent, false)
//        return MessageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
//        holder.messageTextView.text = messages[position].response
//    }
//
//    override fun getItemCount() = messages.size
//
//    fun addMessage(message: ChatResponse) {
//        messages.add(message)
//        notifyItemInserted(messages.size - 1)
//    }
//}

class ChatAdapter(private val messages: MutableList<String>) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message_received, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.messageTextView.text = messages[position]
    }

    override fun getItemCount() = messages.size

    fun addMessage(message: String) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }
}
