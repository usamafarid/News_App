package com.example.news_app.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app.NewsService
import com.example.news_app.R
import com.example.news_app.adapter.NewsAdapter
import com.example.news_app.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter : NewsAdapter
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
        recyclerView=findViewById(R.id.newsList)
    }

    private fun getNews() {
        val news = NewsService.instance.getHeadLines("us",1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(
                call : Call<News?> ,
                success : Response<News?>
            ) {
                val news=success.body()
                if (news!=null){
                    Log.d("News","$news")
                    adapter= NewsAdapter(this@MainActivity,news.articles)
                    recyclerView.adapter=adapter
                    recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(
                call : Call<News?> ,
                t : Throwable
            ) {
                Log.d("UsamaFareed","error in fetching news",t)
            }
        })

    }
}