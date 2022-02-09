package dev.zurbaevi.presentation.history

import dev.zurbaevi.base.UiEvent
import dev.zurbaevi.base.UiState
import dev.zurbaevi.common.base.UiEffect
import dev.zurbaevi.domain.model.Quote

class HistoryContract {

    sealed class HistoryState {
        object Idle : HistoryState()
        object Loading : HistoryState()
        data class Success(val quotes: List<Quote>) : HistoryState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String) : Effect()
    }

    sealed class Event : UiEvent {
        object OnGetQuotes : Event()
    }

    data class State(
        val historyState: HistoryState
    ) : UiState

}