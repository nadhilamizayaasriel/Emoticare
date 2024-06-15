package com.example.emoticare

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.emoticare.data.UserRepository
import com.example.emoticare.data.di.Injection
import com.example.emoticare.login.LoginViewModel
import com.example.emoticare.onboarding.OnboardingViewModel
import com.example.emoticare.profile.ProfileViewModel
import com.example.emoticare.register.RegisterViewModel

class ViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(OnboardingViewModel::class.java)-> {
                OnboardingViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)-> {
                ProfileViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

        }
    }
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                val repository = Injection.provideRepository(context)
                INSTANCE ?: ViewModelFactory(repository).also { INSTANCE = it }
            }
        }

        fun refresh() {
            INSTANCE = null
            Injection.refreshRepository()
        }
    }
}