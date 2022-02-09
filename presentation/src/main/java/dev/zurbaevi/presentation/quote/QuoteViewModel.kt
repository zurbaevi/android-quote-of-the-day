package dev.zurbaevi.presentation.quote

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.base.BaseViewModel
import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.domain.usecase.InsertQuoteUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase,
    private val insertQuoteUseCase: InsertQuoteUseCase
) : BaseViewModel<QuoteContract.Event, QuoteContract.State, QuoteContract.Effect>() {

    override fun createInitialState(): QuoteContract.State {
        return QuoteContract.State(
            quoteState = QuoteContract.QuoteState.Idle
        )
    }

    override fun handleEvent(event: QuoteContract.Event) {
        when (event) {
            is QuoteContract.Event.OnFetchQuote -> {
                fetchQuote()
            }
        }
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            getQuoteUseCase()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (val state = it) {
                        is Resource.Empty -> {
                            setState { copy(quoteState = QuoteContract.QuoteState.Idle) }
                        }
                        is Resource.Loading -> {
                            setState { copy(quoteState = QuoteContract.QuoteState.Loading) }
                        }
                        is Resource.Error -> {
                            setEffect { QuoteContract.Effect.ShowError(state.exception.message.toString()) }
                        }
                        is Resource.Success -> {
                            val quote = state.data
                            setState { copy(quoteState = QuoteContract.QuoteState.Success(quote)) }
                            insertQuote(quote)
                        }
                    }
                }
        }
    }

    private fun insertQuote(quote: Quote) {
        viewModelScope.launch {
            insertQuoteUseCase(quote)
                .collect {
                    if (it is Resource.Error) {
                        setEffect { QuoteContract.Effect.ShowError(it.exception.message.toString()) }
                    }
                }
        }
    }

}