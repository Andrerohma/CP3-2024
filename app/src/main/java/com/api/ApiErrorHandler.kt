package com.example.caremi_kotlin.api

import retrofit2.HttpException
import java.io.IOException

object ApiErrorHandler {
    fun handleException(e: Exception): String {
        return when (e) {
            is IOException -> "Erro de rede, verifique sua conexão."
            is HttpException -> "Erro do servidor: ${e.code()}"
            else -> "Erro desconhecido."
        }
    }
}