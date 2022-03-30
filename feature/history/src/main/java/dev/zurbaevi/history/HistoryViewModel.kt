package dev.zurbaevi.history

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.common.base.BaseViewModel
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.usecase.history.DeleteHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.history.GetHistoryQuotesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryQuotesUseCase: GetHistoryQuotesUseCase,
    private val deleteHistoryQuotesUseCase: DeleteHistoryQuotesUseCase
) : BaseViewModel<HistoryContract.Event, HistoryContract.State, HistoryContract.Effect>() {

    override fun createInitialState(): HistoryContract.State {
        return HistoryContract.State(
            historyState = HistoryContract.HistoryState.Idle,
            quotes = listOf()
        )
    }

    override fun handleEvent(event: HistoryContract.Event) {
        viewModelScope.launch {
            when (event) {
                is HistoryContract.Event.OnGetQuotes -> getQuotes()
                is HistoryContract.Event.OnDeleteQuotes -> deleteQuotes()
            }
        }
    }

    private fun getQuotes() {
        viewModelScope.launch {
            getHistoryQuotesUseCase()
                .onStart { setState { copy(historyState = HistoryContract.HistoryState.Loading) } }
                .catch { setEffect { HistoryContract.Effect.ShowSnackBar(it.message.toString()) } }
                .collect { quotes ->
                    if (checkFavoriteIsEmpty(quotes)) {
                        setState {
                            copy(
                                historyState = HistoryContract.HistoryState.Success,
                                quotes = quotes
                            )
                        }
                    }
                }
        }
    }

    private fun deleteQuotes() {
        viewModelScope.launch {
            deleteHistoryQuotesUseCase()
                .onStart { setState { copy(historyState = HistoryContract.HistoryState.Loading) } }
                .catch { setEffect { HistoryContract.Effect.ShowSnackBar(it.message.toString()) } }
                .collect {
                    setState { copy(historyState = HistoryContract.HistoryState.Empty) }
                    setEffect { HistoryContract.Effect.ShowSnackBarDeleteQuotes }
                }
        }
    }

    private fun checkFavoriteIsEmpty(quotes: List<Quote>): Boolean {
        return if (!quotes.isNullOrEmpty()) {
            true
        } else {
            setState { copy(historyState = HistoryContract.HistoryState.Empty) }
            false
        }
    }

}