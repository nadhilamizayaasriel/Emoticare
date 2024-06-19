package com.example.emoticare.data.response

import com.google.gson.annotations.SerializedName

data class MoodHistoryResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("moodHistory")
	val moodHistory: List<MoodHistoryItem?>? = null
)

data class MoodHistoryItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("mood")
	val mood: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
