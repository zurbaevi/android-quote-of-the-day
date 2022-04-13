package dev.zurbaevi.home

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiState
import dev.zurbaevi.common.base.UiText
import dev.zurbaevi.domain.model.Quote

class HomeContract {

    sealed class HomeState {
        object Idle : HomeState()
        object Loading : HomeState()
        object Error : HomeState()
        object Success : HomeState()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: UiText) : Effect()
    }

    sealed class Event : UiEvent {
        object OnInsertFavoriteQuote : Event()
        object OnDeleteFavoriteQuote : Event()
        object OnCheckFavoriteQuote : Event()
        object OnFetchQuote : Event()
    }

    data class State(
        val homeState: HomeState,
        val quoteIsFavorite: Boolean,
        val quote: Quote
    ) : UiState

}