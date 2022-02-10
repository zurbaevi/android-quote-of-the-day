package dev.zurbaevi.history

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.base.BaseViewModel
import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.domain.usecase.GetQuotesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
) : BaseViewModel<HistoryContract.Event, HistoryContract.State, HistoryContract.Effect>() {

    override fun createInitialState(): HistoryContract.State {
        return HistoryContract.State(
            historyState = HistoryContract.HistoryState.Idle
        )
    }

    override fun handleEvent(event: HistoryContract.Event) {
        viewModelScope.launch {
            when (event) {
                is HistoryContract.Event.OnGetQuotes -> {
                    getQuotes()
                }
            }
        }
    }

    private fun getQuotes() {
        viewModelScope.launch {
            getQuotesUseCase().collect {
                when (val state = it) {
                    is Resource.Empty -> {
                        setState { copy(historyState = HistoryContract.HistoryState.Idle) }
                    }
                    is Resource.Loading -> {
                        setState { copy(historyState = HistoryContract.HistoryState.Loading) }
                    }
                    is Resource.Error -> {
                        setEffect { HistoryContract.Effect.ShowError(state.exception.message.toString()) }
                    }
                    is Resource.Success -> {
                        setState { copy(historyState = HistoryContract.HistoryState.Success(state.data)) }
                    }
                }
            }
        }
    }

}