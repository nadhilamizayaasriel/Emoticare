package com.example.emoticare.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.emoticare.R
import com.example.emoticare.ViewModelFactory
import com.example.emoticare.darkmode.DarkModeActivity
import com.example.emoticare.onboarding.OnboardingActivity

class ProfileFragment : Fragment() {
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val myButton: ImageButton = view.findViewById(R.id.darkMode)
        myButton.setOnClickListener {
            val intent = Intent(activity, DarkModeActivity::class.java).apply {
                putExtra("key", "value")
            }
            startActivity(intent)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.getSession().isLogin) {
            val tvEmail = view.findViewById<TextView>(R.id.textViewEmail)
            val tvName = view.findViewById<TextView>(R.id.textViewName)
            tvEmail.text = viewModel.getSession().email
            tvName.text = viewModel.getSession().name

        }

        setupAction(view)
    }

    private fun setupAction(view: View) {
        val btnLogout = view.findViewById<Button>(R.id.buttonSignOut)
        btnLogout.setOnClickListener {
            viewModel.logout()
            ViewModelFactory.refresh()
            startActivity(Intent(requireActivity(), OnboardingActivity::class.java))
            requireActivity().finish()
        }
    }
}