package dev.zurbaevi.data.local.data_store

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun saveLanguageToDataStore(language: String)
    fun getLanguageFromDataStore(): Flow<String?>
}