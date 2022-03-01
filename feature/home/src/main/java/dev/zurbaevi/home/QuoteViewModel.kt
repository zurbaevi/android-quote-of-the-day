package dev.zurbaevi.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.common.base.BaseViewModel
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.domain.usecase.InsertQuoteUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
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
            is QuoteContract.Event.FetchQuote -> {
                fetchQuote()
            }
        }
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            getQuoteUseCase()
                .onStart {
                    setState { copy(quoteState = QuoteContract.QuoteState.Loading) }
                }
                .catch {
                    setEffect { QuoteContract.Effect.Error(it.message.toString()) }
                }
                .collect { quote ->
                    setState { copy(quoteState = QuoteContract.QuoteState.Success(quote)) }.also {
                        insertQuote(quote)
                    }
                }
        }
    }


    private fun insertQuote(quote: Quote) {
        viewModelScope.launch {
            insertQuoteUseCase(quote)
                .catch {
                    setEffect { QuoteContract.Effect.Error(it.message.toString()) }
                }.collect()
        }
    }

}