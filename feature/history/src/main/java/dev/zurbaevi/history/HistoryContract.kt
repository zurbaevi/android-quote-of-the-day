package dev.zurbaevi.history

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiState
import dev.zurbaevi.domain.model.Quote

class HistoryContract {

    sealed class HistoryState {
        object Idle : HistoryState()
        object Empty : HistoryState()
        object Loading : HistoryState()
        object Success : HistoryState()
    }

    sealed class Effect : UiEffect {
        data class ShowSnackBar(val message: String) : Effect()
        object ShowSnackBarDeleteQuotes : Effect()
    }

    sealed class Event : UiEvent {
        object OnGetQuotes : Event()
        object OnDeleteQuotes : Event()
    }

    data class State(
        val historyState: HistoryState,
        val quotes: List<Quote>
    ) : UiState

}