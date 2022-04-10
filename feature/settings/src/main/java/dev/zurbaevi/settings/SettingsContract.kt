package dev.zurbaevi.settings

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiState

class SettingsContract {

    sealed class SettingsState {
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBarError(val message: String) : Effect()
        data class ShowSnackBarChangeLanguage(val language: String) : Effect()
    }

    sealed class Event : UiEvent {
        data class OnChooseLanguage(val language: String) : Event()
    }

    data class State(
        val settingsState: SettingsState,
    ) : UiState

}