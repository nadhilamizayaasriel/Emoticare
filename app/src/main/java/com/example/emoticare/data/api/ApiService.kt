package com.example.emoticare.data.api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("predict")
    fun sendChatResponse(
        @Body message: RequestBody
    ): Call<ChatResponse>
}