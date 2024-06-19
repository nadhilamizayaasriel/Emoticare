package com.example.emoticare.home

import MoodAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.api.ApiConfig
import com.example.emoticare.data.di.Injection
import com.example.emoticare.data.response.MoodHistoryResponse
import com.example.emoticare.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoodAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel = LoginViewModel(Injection.provideRepository(requireContext()))
        progressBar = view.findViewById(R.id.progressBar)
        adapter = MoodAdapter(emptyList())
        recyclerView.adapter = adapter
        fetchMoods()
        return view
    }

    private fun fetchMoods() {
        showLoading(true)
        val apiService = ApiConfig.getAuthenticatedApiService(viewModel.getSession().token)
        apiService.getAllMoods().enqueue(object : Callback<MoodHistoryResponse> {
            override fun onResponse(call: Call<MoodHistoryResponse>, response: Response<MoodHistoryResponse>) {
                showLoading(false)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.moodHistory?.let { history ->
                        adapter.updateMoods(history.filterNotNull())
                    }
                } else {
                    Toast.makeText(context, "Failed to retrieve mood history: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MoodHistoryResponse>, t: Throwable) {
                showLoading(false)
                Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
}
