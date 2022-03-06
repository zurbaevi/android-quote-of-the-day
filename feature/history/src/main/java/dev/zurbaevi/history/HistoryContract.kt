package dev.zurbaevi.history

import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.common.base.UiEvent
import dev.zurbaevi.common.base.UiState
import dev.zurbaevi.domain.model.Quote

class HistoryContract {

    sealed class HistoryState {
        object Idle : HistoryState()
        object Loading : HistoryState()
        data class Success(val quotes: List<Quote>) : HistoryState()
    }

    sealed class Effect : UiEffect {
        data class Error(val message: String) : Effect()
        object Deleted : Effect()
    }

    sealed class Event : UiEvent {
        object OnGetQuotes : Event()
        object OnDeleteQuotes : Event()
    }

    data class State(
        val historyState: HistoryState
    ) : UiState

}