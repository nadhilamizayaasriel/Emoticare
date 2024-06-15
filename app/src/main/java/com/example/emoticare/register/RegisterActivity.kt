package com.example.emoticare.register

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

        val userPreference = UserPreference.getInstance(dataStore)

        setupAction()

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.registerResponse.observe(this) { registerResponse ->
            if (!registerResponse.error!!) {
                //tambahan
                val name = binding.name.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                viewModel.register(name, email, password)
                AlertDialog.Builder(this).apply {
                    setTitle("Success!")
                    setMessage("your account is ready, Lets login and upload story.")
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
        binding.signinButton.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            viewModel.register(name, email, password)
        }
    }
}
