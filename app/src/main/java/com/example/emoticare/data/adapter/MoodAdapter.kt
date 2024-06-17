import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emoticare.R
import com.example.emoticare.data.response.MoodResponse

class MoodAdapter(private var moods: List<MoodResponse>) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

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
        holder.dateTextView.text = mood.date
        holder.moodTextView.text = mood.mood
    }

    override fun getItemCount() = moods.size

    fun updateData(newMoods: List<MoodResponse>) {
        moods = newMoods
        notifyDataSetChanged()
    }
}
