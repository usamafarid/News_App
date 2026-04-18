package com.example.news_app.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.news_app.NewsService
import com.example.news_app.R
import com.example.news_app.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
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