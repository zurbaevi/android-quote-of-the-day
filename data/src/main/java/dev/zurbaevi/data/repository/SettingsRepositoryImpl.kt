package dev.zurbaevi.data.repository

import dev.zurbaevi.common.util.Language
import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.data.local.data_store.SettingsDataStore
import dev.zurbaevi.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override fun saveLanguageToDataStore(language: String) = flow {
        try {
            emit(Resource.Success(settingsDataStore.saveLanguageToDataStore(language)))
        } catch (exception: Exception) {
            emit(Resource.Error(exception))
        }
    }

    override fun getLanguageFromDataStore() = flow {
        settingsDataStore.getLanguageFromDataStore()
        try {
            emit(Resource.Success(settingsDataStore.getLanguageFromDataStore().first() ?: Language.RUSSIAN))
        } catch (exception: Exception) {
            emit(Resource.Error(exception))
        }
    }

}