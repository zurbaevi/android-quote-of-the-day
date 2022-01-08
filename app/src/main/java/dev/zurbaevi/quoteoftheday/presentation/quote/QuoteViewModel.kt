package dev.zurbaevi.quoteoftheday.presentation.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.quoteoftheday.domain.usecase.InsertQuoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase,
    private val insertQuoteUseCase: InsertQuoteUseCase
) : ViewModel() {

    private val _quoteUiState = MutableLiveData(QuoteUiState())
    val quoteUiState: LiveData<QuoteUiState> get() = _quoteUiState

    init {
        getQuote()
    }

    fun getQuote() {
        viewModelScope.launch {
            _quoteUiState.value = QuoteUiState(isFetchingQuote = true)
            try {
                _quoteUiState.value = QuoteUiState(quote = getQuoteUseCase().apply {
                    insertQuoteUseCase(this)
                })
            } catch (exception: Exception) {
                _quoteUiState.value = QuoteUiState(error = "Something went wrong")
            }
        }
    }

}