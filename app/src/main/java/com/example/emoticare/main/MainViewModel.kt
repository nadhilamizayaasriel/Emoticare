package com.example.emoticare.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.emoticare.data.pref.ModePreferences

class MainViewModel (private val preferences: ModePreferences) : ViewModel() {
    fun getThemeSetting(): LiveData<Boolean> {
        return preferences.getThemeSetting().asLiveData()
    }
}