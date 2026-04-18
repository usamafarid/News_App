package com.example.news_app

import com.example.news_app.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val Base_URL = "https://newsapi.org/"
const val API_KEY = "648ec4574da647dc8ca385079c03ee58"

interface NewsApp {

    //endpoint of url
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country") country : String , @Query("page") page : Int) : Call<News>

    //behind http protocol (https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=un&page=1)
}

//singleton
object NewsService {
    val instance : NewsApp

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(NewsApp::class.java)
    }
}