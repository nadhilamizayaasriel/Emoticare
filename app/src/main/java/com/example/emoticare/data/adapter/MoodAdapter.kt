import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.response.MoodHistoryItem
import java.text.SimpleDateFormat
import java.util.Locale

class MoodAdapter(private var moods: List<MoodHistoryItem>) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    class MoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val moodTextView: TextView = view.findViewById(R.id.moodTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mood_item, parent, false)
        return MoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val mood = moods[position]
        holder.moodTextView.text = mood.mood
        holder.dateTextView.text = formatDate(mood.createdAt)
    }

    private fun formatDate(dateStr: String?): String {
        dateStr ?: return "No Date"
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = inputFormat.parse(dateStr)
            outputFormat.format(date)
        } catch (e: Exception) {
            "Invalid Date"
        }
    }

    override fun getItemCount() = moods.size

    fun updateMoods(newMoods: List<MoodHistoryItem>) {
        moods = newMoods
        notifyDataSetChanged()
    }
}
