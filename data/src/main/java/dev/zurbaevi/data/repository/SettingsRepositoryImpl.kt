package dev.zurbaevi.data.repository

import dev.zurbaevi.common.util.Language
import dev.zurbaevi.data.local.data_store.SettingsDataStore
import dev.zurbaevi.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override fun saveLanguageToDataStore(language: String) = flow {
        emit(settingsDataStore.saveLanguageToDataStore(language))
    }

    override fun getLanguageFromDataStore() = flow {
        emit(settingsDataStore.getLanguageFromDataStore().first() ?: Language.RUSSIAN)
    }

}