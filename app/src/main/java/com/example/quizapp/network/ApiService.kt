package com.example.quizapp.network

import android.content.Context
import com.example.quizapp.model.Question
import com.example.quizapp.utill.Constant.BASE_URL
import io.requestly.rqinterceptor.api.RQCollector
import io.requestly.rqinterceptor.api.RQInterceptor
import okhttp3.OkHttpClient

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("MoviesQuestions")
    fun getQuizzes(): Call<List<Question>>

    companion object{
        private var apiService :  ApiService? = null

        fun getInstance(context: Context): ApiService? {
            val collector = RQCollector(context, sdkKey="7bM6bDFIVlc2may0uEW7")
            val rqInterceptor = RQInterceptor.Builder(context).collector(collector).build()
            val client = OkHttpClient.Builder().addInterceptor(rqInterceptor).build()
            if(apiService == null) {
             val retrofit = Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .client(client)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
