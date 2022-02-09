package dev.zurbaevi.presentation.quote

import dev.zurbaevi.base.UiEvent
import dev.zurbaevi.base.UiState
import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.domain.model.Quote

class QuoteContract {

    sealed class QuoteState {
        object Idle : QuoteState()
        object Loading : QuoteState()
        data class Success(val quote: Quote) : QuoteState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String) : Effect()
    }

    sealed class Event : UiEvent {
        object OnFetchQuote : Event()
    }

    data class State(
        val quoteState: QuoteState
    ) : UiState

}