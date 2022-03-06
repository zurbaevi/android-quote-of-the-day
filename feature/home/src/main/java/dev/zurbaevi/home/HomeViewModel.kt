package dev.zurbaevi.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.common.base.BaseViewModel
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.usecase.FetchQuoteUseCase
import dev.zurbaevi.domain.usecase.InsertHistoryQuoteUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchQuoteUseCase: FetchQuoteUseCase,
    private val insertHistoryQuoteUseCase: InsertHistoryQuoteUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(
            homeState = HomeContract.HomeState.Idle
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.FetchQuote -> {
                fetchQuote()
            }
        }
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            fetchQuoteUseCase()
                .onStart {
                    setState { copy(homeState = HomeContract.HomeState.Loading) }
                }
                .catch {
                    setEffect { HomeContract.Effect.Error(it.message.toString()) }
                }
                .collect { quote ->
                    setState { copy(homeState = HomeContract.HomeState.Success(quote)) }.also {
                        insertQuote(quote)
                    }
                }
        }
    }


    private fun insertQuote(quote: Quote) {
        viewModelScope.launch {
            insertHistoryQuoteUseCase(quote)
                .catch {
                    setEffect { HomeContract.Effect.Error(it.message.toString()) }
                }.collect()
        }
    }

}