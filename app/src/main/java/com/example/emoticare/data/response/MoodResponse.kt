package com.example.emoticare.data.response

import com.google.gson.annotations.SerializedName

data class MoodResponse(
    @SerializedName("createdAt")
    val date: String,
    val mood: String
)
