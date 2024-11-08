package com.example.caremi_kotlin.api

data class ApiResponse<T>(
    val data: T?,
    val message: String?,
    val success: Boolean
)