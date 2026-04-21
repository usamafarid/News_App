package com.example.news_app.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.model.Article


class NewsAdapter(val context : Context,val article : List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){
    override fun onCreateViewHolder(
        parent : ViewGroup ,
        viewType : Int
    ) : ArticleViewHolder {
        val  view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(
        holder : ArticleViewHolder ,
        position : Int
    ) {
      val articles=article[position]

        holder.titleText.text=articles.title
        holder.descriptionText.text=articles.description
        Glide.with(context).load(articles.urlToImage).into(holder.imageView)
    }

    override fun getItemCount() : Int {
     return article.size
    }

    class ArticleViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val imageView=itemView.findViewById<ImageView>(R.id.newsImage)
        val titleText=itemView.findViewById<TextView>(R.id.newsTitle)
        val descriptionText=itemView.findViewById<TextView>(R.id.newsDescription)
    }
}
