package com.example.emoticare.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.CalendarView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.api.ApiConfig
import com.example.emoticare.data.response.MoodResponse
import com.example.emoticare.data.di.Injection
import com.example.emoticare.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class HomeFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var calendarView: CalendarView
    private lateinit var articlesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        calendarView = view.findViewById(R.id.calendarView)
        articlesRecyclerView = view.findViewById(R.id.articlesRecyclerView)
        viewModel = LoginViewModel(Injection.provideRepository(requireContext()))
        setupRecyclerView()
        setupCalendar()
        return view
    }

    private fun setupRecyclerView() {
        // Inisialisasi RecyclerView dengan adapter dan layout manager
        articlesRecyclerView.layoutManager = LinearLayoutManager(context)
        articlesRecyclerView.adapter =
            ArticlesAdapter(listOf("Article 1", "Article 2", "Article 3"))
    }

    private fun setupCalendar() {
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val formattedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            val options = arrayOf("Natural", "Happy", "Sad")
            AlertDialog.Builder(context).apply {
                setTitle("Select Your Mood on $formattedDate")
                setItems(options) { dialog, which ->
                    val selectedMood = options[which].toLowerCase()
                    checkAndSetMood(selectedMood, formattedDate)
                }
                show()
            }
        }
    }

    private fun checkAndSetMood(mood: String, date: String) {
        val apiService = ApiConfig.getAuthenticatedApiService(viewModel.getSession().token)
        apiService.getTodayMood().enqueue(object : Callback<MoodResponse> {
            override fun onResponse(call: Call<MoodResponse>, response: Response<MoodResponse>) {
                if (response.isSuccessful) {
                    updateMood(mood, date)
                } else {
                    createMood(mood, date)
                }
            }

            override fun onFailure(call: Call<MoodResponse>, t: Throwable) {
                Toast.makeText(context, "Error checking today's mood: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateMood(mood: String, date: String) {
        val apiService = ApiConfig.getAuthenticatedApiService(viewModel.getSession().token)
        apiService.updateMood(date, mood).enqueue(object : Callback<MoodResponse> {
            override fun onResponse(call: Call<MoodResponse>, response: Response<MoodResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Mood updated successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to update mood: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MoodResponse>, t: Throwable) {
                Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createMood(mood: String, date: String) {
        val apiService = ApiConfig.getAuthenticatedApiService(viewModel.getSession().token)
        apiService.createMood(date, mood).enqueue(object : Callback<MoodResponse> {
            override fun onResponse(call: Call<MoodResponse>, response: Response<MoodResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Mood saved successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to save mood: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MoodResponse>, t: Throwable) {
                Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
