package com.example.viewmodelsandapitutorial.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Post(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
)

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface ApiService{
    @GET("posts")
    suspend fun getTodos(): List<Post>

    companion object{
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}