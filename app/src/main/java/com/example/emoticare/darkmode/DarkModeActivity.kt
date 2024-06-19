package com.example.emoticare.darkmode

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.emoticare.R
import com.example.emoticare.ViewModelFactory
import com.example.emoticare.databinding.ActivityDarkModeBinding

class DarkModeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDarkModeBinding
    private val modeViewModel: DarkModeViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDarkModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.elevation = 0f

        modeViewModel.getThemeSetting().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            modeViewModel.saveThemeSetting(isChecked)
        }

        // Menginisialisasi tombol kembali dengan sintaks yang benar
        val myButton: ImageButton = binding.backBtn
        myButton.setOnClickListener {
            // Menambahkan fungsi kembali ke halaman sebelumnya
            onBackPressed()
        }
    }
}