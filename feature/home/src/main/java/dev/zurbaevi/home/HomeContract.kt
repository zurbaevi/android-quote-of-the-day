package dev.zurbaevi.home

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiState
import dev.zurbaevi.domain.model.Quote

class HomeContract {

    sealed class HomeState {
        object Idle : HomeState()
        object Loading : HomeState()
        data class Success(val quote: Quote) : HomeState()
    }

    sealed class Effect : UiEffect {
        data class Error(val message: String) : Effect()
    }

    sealed class Event : UiEvent {
        object FetchQuote : Event()
    }

    data class State(
        val homeState: HomeState,
    ) : UiState

}