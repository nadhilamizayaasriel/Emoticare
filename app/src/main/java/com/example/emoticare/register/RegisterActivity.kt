package com.example.emoticare.register

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emoticare.R
import com.example.emoticare.ViewModelFactory
import com.example.emoticare.data.pref.UserPreference
import com.example.emoticare.data.pref.dataStore
import com.example.emoticare.databinding.ActivityRegisterBinding
import com.example.emoticare.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        playAnimation()


        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.registerResponseLiveData.observe(this) { registerResponse ->
            if (!registerResponse.error!!) {
                //tambahan
                AlertDialog.Builder(this).apply {
                    setTitle("Success!")
                    setMessage("Yeay your account has already, let's login.")
                    setPositiveButton("Next") { _, _ ->
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                        Log.d("Register", "berhasil")
                    }
                    create()
                    show()
                }
            } else if (registerResponse.message == "Email is already taken") {
                AlertDialog.Builder(this).apply {
                    setTitle("Failed!")
                    setMessage("Email has already use.")
                    setPositiveButton("Next") { _, _ ->
                    }
                    create()
                    show()
                }
            } else {
                AlertDialog.Builder(this).apply {
                    setTitle("Failed")
                    setMessage("Something Wrong.")
                    setPositiveButton("Next") { _, _ ->
                    }
                    create()
                    show()
                }
            }
        }
    }

    private fun setupAction() {
        binding.registerBtn.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.register(name, email, password)
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

    }
}
