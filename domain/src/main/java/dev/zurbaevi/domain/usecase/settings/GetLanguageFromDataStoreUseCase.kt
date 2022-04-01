package dev.zurbaevi.domain.usecase.settings

import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetLanguageFromDataStoreUseCase(
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Resource<String>> {
        return settingsRepository.getLanguageFromDataStore().flowOn(dispatcher)
    }

}