package com.example.emoticare.data.api

import com.example.emoticare.data.response.ChatResponse
import com.example.emoticare.data.response.LoginResponse
import com.example.emoticare.data.response.MoodResponse
import com.example.emoticare.data.response.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("predict")
    fun sendChatResponse(
        @Body message: RequestBody
    ): Call<ChatResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @POST("mood/create/{date}/{mood}")
    fun createMood(
        @Path("date") date: String,
        @Path("mood") mood: String
    ): Call<MoodResponse>

    @PUT("mood/update/{date}/{mood}")
    fun updateMood(
        @Path("date") date: String,
        @Path("mood") mood: String
    ): Call<MoodResponse>

    @GET("mood/{date}")
    fun getTodayMood(
        @Path("date") date: String
    ): Call<MoodResponse>

    @GET("mood")
    fun getAllMoods(): Call<List<MoodResponse>>

}