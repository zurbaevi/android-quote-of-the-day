package dev.zurbaevi.history

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.common.base.BaseViewModel
import dev.zurbaevi.domain.usecase.DeleteHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.GetHistoryQuotesUseCase
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
            historyState = HistoryContract.HistoryState.Idle
        )
    }

    override fun handleEvent(event: HistoryContract.Event) {
        viewModelScope.launch {
            when (event) {
                is HistoryContract.Event.GetQuotes -> {
                    getQuotes()
                }
                is HistoryContract.Event.DeleteQuotes -> {
                    deleteQuotes()
                }
            }
        }
    }

    private fun getQuotes() {
        viewModelScope.launch {
            getHistoryQuotesUseCase()
                .onStart {
                    setState { copy(historyState = HistoryContract.HistoryState.Loading) }
                }
                .catch {
                    setEffect { HistoryContract.Effect.Error(it.message.toString()) }
                }
                .collect { quotes ->
                    setState {
                        copy(historyState = HistoryContract.HistoryState.Success(quotes))
                    }
                }
        }
    }

    private fun deleteQuotes() {
        viewModelScope.launch {
            deleteHistoryQuotesUseCase()
                .onStart {
                    setState { copy(historyState = HistoryContract.HistoryState.Loading) }
                }
                .catch {
                    setEffect { HistoryContract.Effect.Error(it.message.toString()) }
                }
                .collect {
                    setState { copy(historyState = HistoryContract.HistoryState.Success(listOf())) }.also {
                        setEffect { HistoryContract.Effect.Deleted }
                    }
                }
        }
    }

}