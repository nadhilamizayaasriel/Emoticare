package com.example.emoticare.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.emoticare.main.MainActivity
import com.example.emoticare.R
import com.example.emoticare.onboarding.OnboardingActivity

class SplashActivty : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activty)
        splashViewModel.isLoggedIn.observe(this, Observer { isLoggedIn ->
            if (isLoggedIn) {
                // Jika sudah login, arahkan ke MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Jika belum login, arahkan ke LoginActivity
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
                Log.d("navigateToLoginActivity", "aku terpanggil")

            }
        })
    }
}