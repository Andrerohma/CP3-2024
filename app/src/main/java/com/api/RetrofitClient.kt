package com.example.caremi_kotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: PlantaApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://sua-api.com/")  // Substitua pela URL da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlantaApiService::class.java)
    }
}