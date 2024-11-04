package com.example.caremi_kotlin.api

import com.example.caremi_kotlin.model.Planta
import retrofit2.Response
import retrofit2.http.*

interface PlantaApiService {
    @GET("plantas")
    suspend fun getPlantas(): Response<List<Planta>>

    @POST("plantas")
    suspend fun createPlanta(@Body planta: Planta): Response<Planta>

    @PUT("plantas/{id}")
    suspend fun updatePlanta(@Path("id") id: Int, @Body planta: Planta): Response<Planta>

    @DELETE("plantas/{id}")
    suspend fun deletePlanta(@Path("id") id: Int): Response<Unit>
}