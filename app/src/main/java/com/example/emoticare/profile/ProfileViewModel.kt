package com.example.emoticare.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoticare.data.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileViewModel (private val repository: UserRepository) : ViewModel() {
    fun getSession() = runBlocking { repository.getSession().first() }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}