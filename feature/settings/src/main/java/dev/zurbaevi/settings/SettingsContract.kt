package dev.zurbaevi.settings

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiText

class SettingsContract {

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: UiText) : Effect()
    }

    sealed class Event : UiEvent {
        data class OnChooseLanguage(val language: String) : Event()
    }

}