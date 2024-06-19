package com.example.emoticare.onboarding

import androidx.lifecycle.ViewModel
import com.example.emoticare.data.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class OnboardingViewModel
    (private val repository: UserRepository) : ViewModel() {

    fun getSession() = runBlocking {
        repository.getSession().first()
    }
}