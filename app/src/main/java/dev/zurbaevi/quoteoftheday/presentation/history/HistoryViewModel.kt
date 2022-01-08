package dev.zurbaevi.quoteoftheday.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuotesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
) : ViewModel() {

    private val _historyUiState = MutableLiveData(HistoryUiState())
    val historyUiState: LiveData<HistoryUiState> get() = _historyUiState

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            _historyUiState.value = HistoryUiState(isFetchingQuote = true)
            try {
                _historyUiState.value = HistoryUiState(quotes = getQuotesUseCase())
            } catch (exception: Exception) {
                _historyUiState.value = HistoryUiState(error = "Error Occurred!")
            }
        }
    }

}