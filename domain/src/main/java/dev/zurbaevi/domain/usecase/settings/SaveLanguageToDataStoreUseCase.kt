package dev.zurbaevi.domain.usecase.settings

import dev.zurbaevi.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SaveLanguageToDataStoreUseCase(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(language: String): Flow<Unit> {
        return settingsRepository.saveLanguageToDataStore(language).flowOn(dispatcher)
    }

}