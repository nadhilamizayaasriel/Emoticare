//package com.example.emoticare.home
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.emoticare.R
//
//class CalendarAdapter(private val days: List<DayInfo>) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {
//    class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val dayText: TextView = view.findViewById(R.id.dayText)
//        val emojiImage: ImageView = view.findViewById(R.id.emojiImage)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_item_layout, parent, false)
//        return DayViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
//        val day = days[position]
//        holder.dayText.text = day.dayOfMonth.toString()
//        holder.emojiImage.setImageResource(day.emojiResourceId)
//    }
//
//    override fun getItemCount() = days.size
//}
//
//data class DayInfo(val dayOfMonth: Int, val emojiResourceId: Int)
