package dev.zurbaevi.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun saveLanguageToDataStore(language: String): Flow<Unit>
    fun getLanguageFromDataStore(): Flow<String>
}