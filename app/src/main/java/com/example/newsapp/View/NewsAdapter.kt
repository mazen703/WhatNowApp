package com.example.newsapp.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsapp.R
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.ArticleListItemBinding
class NewsAdapter(
    private var newslist: List<Article>,
    private val onItemClick: (Article) -> Unit,
    private val onShareClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ArticleListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            ArticleListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = newslist[position]

        holder.binding.text.text = article.title

        Glide.with(holder.binding.image.context)
            .load(article.imageUrl)
            .error(R.drawable.outline_broken_image_24)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(holder.binding.image)

        holder.binding.articleContainer.setOnClickListener {
            onItemClick(article)
        }

        holder.binding.shareFbtn.setOnClickListener {
            onShareClick(article)
        }
    }

    override fun getItemCount() = newslist.size

    fun updateData(newList: List<Article>) {
        newslist = newList
        notifyDataSetChanged()
    }
}