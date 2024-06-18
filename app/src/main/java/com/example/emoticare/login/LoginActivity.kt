package com.example.emoticare.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.emoticare.MainActivity
import com.example.emoticare.R
import com.example.emoticare.ViewModelFactory
import com.example.emoticare.data.pref.UserModel
import com.example.emoticare.databinding.ActivityLoginBinding
import com.example.emoticare.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLoginText()
        setupAction()
    }

    private fun setupLoginText() {
        val textView: TextView = binding.registerClick
        val text = getString(R.string.register)
        val spannableString = SpannableString(text)
        val customColor = ContextCompat.getColor(this, R.color.blue)
        val colorSpan = ForegroundColorSpan(customColor)

        spannableString.setSpan(
            colorSpan,
            0,
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
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
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            viewModel.login(email, password)
        }

        viewModel.loginResponseLiveData.observe(this) { result ->
            if (result != null) {
                // Save session if login is successful
                viewModel.saveSession(
                    UserModel(
                        name =  result.loginResult?.name.toString() ,
                        email = binding.emailInput.text.toString(),
                        token = result.loginResult?.token.toString(),
                        isLogin = true
                    )
                )
                val action = Intent(this, MainActivity::class.java)
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
