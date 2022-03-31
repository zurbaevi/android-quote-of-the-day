package dev.zurbaevi.data.local.data_store

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun saveToSettingsDataStore(language: String)
    fun getFromSettingsDataStore(): Flow<String?>
}