package dev.zurbaevi.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStoreImpl @Inject constructor(private val context: Context): SettingsDataStore {

    private val Context.dataStore by preferencesDataStore(name = SETTINGS_DATASTORE_NAME)

    override suspend fun saveLanguageToDataStore(language: String) {
        context.dataStore.edit {
            it[LANGUAGE] = language
        }
    }

    override fun getLanguageFromDataStore() = context.dataStore.data.map {
        it[LANGUAGE]
    }

    companion object {
        const val SETTINGS_DATASTORE_NAME = "Settings Datastore"
        val LANGUAGE = stringPreferencesKey("LANGUAGE")
    }

}