package com.example.emoticare.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emoticare.data.UserRepository
import com.example.emoticare.data.pref.UserModel
import com.example.emoticare.data.response.RegisterResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel (private val repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerResponseLiveData: LiveData<RegisterResponse> = _registerResponseLiveData

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.register(name, email, password)
                _registerResponseLiveData.value = response
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, RegisterResponse::class.java)
                val errorMessage = errorBody.message
                _registerResponseLiveData.value = RegisterResponse(true,errorMessage)
            } catch (e: Exception) {
                _registerResponseLiveData.value = RegisterResponse(true, e.message.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }
}