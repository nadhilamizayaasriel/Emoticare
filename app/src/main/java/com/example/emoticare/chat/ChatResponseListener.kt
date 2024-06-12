package com.example.emoticare.chat

interface ChatResponseListener {
    fun onResponse(response: String)
    fun onError(message: String)
}