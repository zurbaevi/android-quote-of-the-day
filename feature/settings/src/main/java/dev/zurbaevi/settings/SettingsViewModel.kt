package dev.zurbaevi.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.domain.usecase.settings.SaveLanguageToDataStoreUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveLanguageToDataStoreUseCase: SaveLanguageToDataStoreUseCase,
) : ViewModel() {

    private val _event: MutableSharedFlow<SettingsContract.Event> = MutableSharedFlow()
    private val event = _event.asSharedFlow()

    private val _effect: Channel<SettingsContract.Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    private fun handleEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.OnChooseLanguage -> {
                saveLanguageToDataStore(event.language)
            }
        }
    }

    private fun saveLanguageToDataStore(currentLanguage: String) {
        viewModelScope.launch {
            saveLanguageToDataStoreUseCase(currentLanguage)
                .catch { setEffect { SettingsContract.Effect.ShowSnackBarError(it.message.toString()) } }
                .collect {
                    setEffect {
                        SettingsContract.Effect.ShowSnackBarChangeLanguage(
                            currentLanguage
                        )
                    }
                }
        }
    }

    fun setEvent(event: SettingsContract.Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    private fun setEffect(builder: () -> SettingsContract.Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

}
