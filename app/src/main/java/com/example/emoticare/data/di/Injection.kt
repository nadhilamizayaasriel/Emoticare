package com.example.emoticare.data.di

import android.content.Context
import com.example.emoticare.data.UserRepository
import com.example.emoticare.data.api.ApiConfig
import com.example.emoticare.data.pref.UserPreference
import com.example.emoticare.data.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getAuthenticatedApiService(user.token)
        return UserRepository.getInstance(apiService, pref)
    }

    fun refreshRepository() {
        UserRepository.refresh()
    }
}