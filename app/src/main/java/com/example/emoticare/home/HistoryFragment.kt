package com.example.emoticare.home

import MoodAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.api.ApiConfig
import com.example.emoticare.data.response.MoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MoodAdapter(emptyList())
        recyclerView.adapter = adapter
        fetchMoods()
        return view
    }

    private fun fetchMoods() {
        val apiService = ApiConfig.getAuthenticatedApiService(token = "token")
        apiService.getAllMoods().enqueue(object : Callback<List<MoodResponse>> {
            override fun onResponse(call: Call<List<MoodResponse>>, response: Response<List<MoodResponse>>) {
                if (response.isSuccessful) {
                    adapter = MoodAdapter(response.body()!!)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(context, "Failed to retrieve moods", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MoodResponse>>, t: Throwable) {
                Toast.makeText(context, "Error loading moods: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
