package com.example.emoticare.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var calendarView: CalendarView
    private lateinit var articlesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        calendarView = view.findViewById(R.id.calendarView)
        articlesRecyclerView = view.findViewById(R.id.articlesRecyclerView)
        setupRecyclerView()
        setupCalendar()
        return view
    }

    private fun setupRecyclerView() {
        // Inisialisasi RecyclerView dengan adapter dan layout manager
        articlesRecyclerView.layoutManager = LinearLayoutManager(context)
        articlesRecyclerView.adapter = ArticlesAdapter(listOf("Article 1", "Article 2", "Article 3"))
    }

    private fun setupCalendar() {
        // Mengatur event listener untuk kalender
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Tangani perubahan tanggal di sini
        }
    }
}
