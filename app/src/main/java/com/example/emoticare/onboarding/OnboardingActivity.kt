package com.example.emoticare.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.emoticare.MainActivity
import com.example.emoticare.R
import com.example.emoticare.ViewModelFactory
import com.example.emoticare.login.LoginActivity
import com.example.emoticare.register.RegisterActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var handler: Handler
    private var currentPage = 0

    private val viewModel by viewModels<OnboardingViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)

        if (viewModel.getSession().isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            val images = listOf(
                R.drawable.onboarding_image_1,
                R.drawable.onboarding_image_2,
                R.drawable.onboarding_image_3,
                R.drawable.onboarding_image_4,
                R.drawable.onboarding_image_5
            )

            viewPager.adapter = ViewPagerAdapter(images)
            handler = Handler(Looper.getMainLooper())

            val runnable = object : Runnable {
                override fun run() {
                    if (currentPage == images.size) {
                        currentPage = 0
                    }
                    viewPager.setCurrentItem(currentPage++, true)
                    handler.postDelayed(this, 3000)
                }
            }
            handler.post(runnable)
        }

        setupAction()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::handler.isInitialized) {
            handler.removeCallbacksAndMessages(null)
        }
    }

    private fun setupAction() {
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignIn = findViewById<Button>(R.id.btnSignin)

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Log.d("btnLogin", "Login button clicked")
        }

        btnSignIn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            Log.d("btnSignIn", "Sign in button clicked")
        }
    }
}