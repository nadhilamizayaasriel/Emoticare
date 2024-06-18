package com.example.emoticare.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emoticare.R
import com.example.emoticare.data.pref.Article

class ArticlesAdapter(private var articles: List<Article>) : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    fun updateData(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textTitle: TextView = itemView.findViewById(R.id.textViewName)
        val textDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewStory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        Glide.with(holder.itemView)
            .load(article.photo)
            .placeholder(R.drawable.picture)
            .error(R.drawable.baseline_error_outline_24)
            .into(holder.imageView)

        holder.textTitle.text = article.title
        holder.textDescription.text = article.description

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(article)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
