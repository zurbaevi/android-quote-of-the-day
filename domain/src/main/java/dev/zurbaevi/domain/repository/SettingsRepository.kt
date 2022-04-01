package dev.zurbaevi.domain.repository

import dev.zurbaevi.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun saveLanguageToDataStore(language: String): Flow<Resource<Unit>>
    fun getLanguageFromDataStore(): Flow<Resource<String>>
}