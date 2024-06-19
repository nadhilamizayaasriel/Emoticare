package com.example.emoticare.darkmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.emoticare.data.pref.ModePreferences
import kotlinx.coroutines.launch

class DarkModeViewModel (private val preferences: ModePreferences): ViewModel() {
    fun getThemeSetting(): LiveData<Boolean> {
        return preferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive:Boolean){
        viewModelScope.launch {
            preferences.saveThemeSetting(isDarkModeActive)
        }
    }
}