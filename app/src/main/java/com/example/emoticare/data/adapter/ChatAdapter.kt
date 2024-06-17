package com.example.emoticare.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.api.Message

class ChatAdapter(private val messages: MutableList<Message>) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSent) {
            R.layout.item_message_received  // Use sent layout for responses
        } else {
            R.layout.item_message_sent  // Use received layout for sent messages
        }
    }

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.messageTextView.text = messages[position].message
    }

    override fun getItemCount() = messages.size

    fun addMessage(message: Message) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }
}
