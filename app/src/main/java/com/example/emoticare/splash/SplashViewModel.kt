package com.example.emoticare.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel(){
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private  val _isLoggedIn = MutableLiveData<Boolean>()

    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            // Simulasi proses loading data, seperti fetching dari server atau database
            delay(2000)
            _isLoading.value = false

            checkLoginStatus()
        }
    }

    fun checkLoginStatus() {
        val isLoggedInNow = isUserLoggedIn()
        _isLoggedIn.value = isLoggedInNow
    }

    private fun isUserLoggedIn(): Boolean {
        return false
    }
}
