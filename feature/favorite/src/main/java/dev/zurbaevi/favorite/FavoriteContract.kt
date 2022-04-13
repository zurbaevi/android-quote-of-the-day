package dev.zurbaevi.favorite

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiState
import dev.zurbaevi.common.base.UiText
import dev.zurbaevi.domain.model.Quote

class FavoriteContract {

    sealed class FavoriteState {
        object Idle : FavoriteState()
        object Empty : FavoriteState()
        object Loading : FavoriteState()
        object Success : FavoriteState()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: UiText) : Effect()
    }

    sealed class Event : UiEvent {
        object OnGetQuotes : Event()
        object OnDeleteQuotes : Event()
        data class OnDeleteQuote(val quote: Quote) : Event()
        data class OnUpdateQuote(val quotes: List<Quote>) : Event()
    }

    data class State(
        val favoriteState: FavoriteState,
        val quotes: List<Quote>
    ) : UiState

}