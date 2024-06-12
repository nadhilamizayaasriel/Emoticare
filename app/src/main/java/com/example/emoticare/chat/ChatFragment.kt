package com.example.emoticare.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.api.ApiConfig
import com.example.emoticare.data.api.ChatAdapter
import com.example.emoticare.data.api.ChatResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
//
//class ChatFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private var responseListener: ChatResponseListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_chat, container, false)
//    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ChatFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}


class ChatFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var messageInput: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageInput = view.findViewById(R.id.messageInput)
        sendButton = view.findViewById(R.id.sendButton)
        recyclerView = view.findViewById(R.id.chatRecyclerView)

        chatAdapter = ChatAdapter(mutableListOf())
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString()
            if (messageText.isNotEmpty()) {
                sendMessage(messageText)
                messageInput.setText("")
                chatAdapter.addMessage(messageText)
            }
        }
    }

//    private fun sendMessage(message: String) {
//        val requestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), message)
//        val call = ApiConfig.getApiService().sendChatResponse(requestBody)
//        call.enqueue(object : Callback<ChatResponse> {
//            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
//                if (response.isSuccessful) {
//                    val chatResponse = response.body()
//                    chatResponse?.let {
//                        it.response?.let { it1 -> updateChat(it1) }
//                    }
//                } else {
//                    // Handle the case where the server responds with an error status.
//                    Log.e("ChatFragment", "Response error: ${response.errorBody()?.string()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
//                // Handle the case where the call fails, e.g., due to network error.
//                Log.e("ChatFragment", "Network error: ${t.message}")
//            }
//        })
//    }

    private fun sendMessage(message: String) {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = "{\"message\":\"$message\"}".toRequestBody(mediaType)

        val call = ApiConfig.getApiService().sendChatResponse(requestBody)
        call.enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val chatResponse = response.body()
                    chatResponse?.let {
                        it.response?.let { it1 -> updateChat(it1) }
                    }
                } else {
                    Log.e("ChatFragment", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.e("ChatFragment", "Network error: ${t.message}")
            }
        })
    }

    private fun updateChat(response: String) {
        chatAdapter.addMessage(response)
        recyclerView.scrollToPosition(chatAdapter.itemCount - 1)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}