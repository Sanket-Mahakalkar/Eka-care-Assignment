package com.example.assignment.adapters

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.model.Article

class NewsAdapter(private val isFromSavedScreen: Boolean):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>()  {

    private val diffUtil = object : DiffUtil.ItemCallback<Article> (){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private var onReadMoreClick: ((Article) -> Unit)? = null
    private var onDeleteClick: ((Int) -> Unit)? = null

    private val differ = AsyncListDiffer(this, diffUtil)
    var newsList : List<Article>
        set(value) = differ.submitList(value)
        get() = differ.currentList

    fun setOnReadMoreClickListener(listener: (Article) -> Unit) {
        onReadMoreClick = listener
    }
    fun setOnDeleteClickListener(listener: (Int) -> Unit) {
        onDeleteClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])

    }

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvTitle: TextView = itemView. findViewById<TextView>(R.id.tv_heading)
        private val tvDesc: TextView = itemView.findViewById<TextView>(R.id.tv_desc)
        private val ivNews: ImageView = itemView.findViewById<ImageView>(R.id.iv_news)
        private val ivDelete: ImageView = itemView.findViewById<ImageView>(R.id.iv_delete)

        fun bind(news: Article){
            tvTitle.text = news.title
            ivDelete.visibility = if (isFromSavedScreen) View.VISIBLE else View.GONE
            Glide.with(itemView).load(news.urlToImage).into(ivNews)
            setDescriptionWithReadMore(news)
            ivDelete.setOnClickListener {
                onDeleteClick?.invoke(newsList.indexOf(news))
            }

        }

        private fun setDescriptionWithReadMore(news: Article) {
            val description = if ((news.description?:"").length > 100) {
                news.description?.substring(0, 100) + "..."
            } else {
                news.description?:""
            }
            val fullText = "$description Read More"
            val spannable = SpannableString(fullText)
            val readMoreStart = description.length + 1
            val readMoreEnd = fullText.length
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.blue)),
                readMoreStart,
                readMoreEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        onReadMoreClick?.invoke(news)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = true
                    }
                },
                readMoreStart,
                readMoreEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            tvDesc.text = spannable
            tvDesc.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}