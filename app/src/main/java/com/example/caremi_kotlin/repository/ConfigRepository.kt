package com.example.caremi_kotlin.repository

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit

// Define a extensão do DataStore no contexto
private val Context.dataStore by preferencesDataStore(name = "planta_config")

class ConfigRepository(private val context: Context) {
    private val TEMPO_SOL_KEY = stringPreferencesKey("TEMPO_NO_SOL")
    private val INTERVALO_AGUA_KEY = stringPreferencesKey("INTERVALO_DE_AGUA")

    // Retorna o tempo no sol armazenado
    val tempoNoSol: Flow<String> = context.dataStore.data
        .map { it[TEMPO_SOL_KEY] ?: "" }

    // Salva o tempo no sol
    suspend fun setTempoNoSol(tempoNoSol: String) {
        context.dataStore.edit { it[TEMPO_SOL_KEY] = tempoNoSol }
    }

    // Retorna o intervalo de água armazenado
    val intervaloDeAgua: Flow<String> = context.dataStore.data
        .map { it[INTERVALO_AGUA_KEY] ?: "" }

    // Salva o intervalo de água
    suspend fun setIntervaloDeAgua(intervaloDeAgua: String) {
        context.dataStore.edit { it[INTERVALO_AGUA_KEY] = intervaloDeAgua }
    }
}