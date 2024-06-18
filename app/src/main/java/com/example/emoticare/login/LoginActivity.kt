package com.example.emoticare.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.emoticare.MainActivity
import com.example.emoticare.ViewModelFactory
import com.example.emoticare.data.pref.UserModel
import com.example.emoticare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    override fun onResume() {
        super.onResume()
        // Check if the user is already logged in
        if (viewModel.getSession().isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            viewModel.login(email, password)
        }

        viewModel.loginResponseLiveData.observe(this) { result ->
            if (result != null) {
                // Save session if login is successful
                viewModel.saveSession(
                    UserModel(
                        email = binding.email.text.toString(),
                        token = result.loginResult?.token.toString(),
                        isLogin = true
                    )
                )
                AlertDialog.Builder(this).apply {
                    setTitle("Yeay!")
                    setMessage("Login success.")
                    setPositiveButton("Next") { _, _ ->
                        // Refresh ViewModelFactory (if needed)
                        ViewModelFactory.refresh()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                    create()
                    show()
                }
            } else {
                // Show error dialog if login fails
                AlertDialog.Builder(this)
                    .setTitle("Failed!")
                    .setMessage("Login failed. Please try again.")
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        // Observe isLoading to show/hide progress bar
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}
