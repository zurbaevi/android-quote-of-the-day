package dev.zurbaevi.favorite

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.common.base.BaseViewModel
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.usecase.favorite.DeleteFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.favorite.DeleteFavoriteQuotesUseCase
import dev.zurbaevi.domain.usecase.favorite.GetFavoriteQuotesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteQuotesUseCase: GetFavoriteQuotesUseCase,
    private val deleteFavoriteQuoteUseCase: DeleteFavoriteQuoteUseCase,
    private val deleteFavoriteQuotesUseCase: DeleteFavoriteQuotesUseCase
) : BaseViewModel<FavoriteContract.Event, FavoriteContract.State, FavoriteContract.Effect>() {

    override fun createInitialState(): FavoriteContract.State {
        return FavoriteContract.State(
            favoriteState = FavoriteContract.FavoriteState.Idle,
            quotes = listOf()
        )
    }

    override fun handleEvent(event: FavoriteContract.Event) {
        when (event) {
            is FavoriteContract.Event.OnDeleteQuote -> deleteFavoriteQuote(event.quote)
            is FavoriteContract.Event.OnGetQuotes -> getFavoriteQuotes()
            is FavoriteContract.Event.OnUpdateQuote -> {
                updateQuotes(event.quotes)
                if (event.quotes.isEmpty()) {
                    setState { copy(favoriteState = FavoriteContract.FavoriteState.Empty) }
                }
            }
            is FavoriteContract.Event.OnDeleteQuotes -> deleteFavoriteQuotes()
        }
    }

    private fun getFavoriteQuotes() {
        viewModelScope.launch {
            getFavoriteQuotesUseCase()
                .onStart { setState { copy(favoriteState = FavoriteContract.FavoriteState.Loading) } }
                .catch { setEffect { FavoriteContract.Effect.ShowSnackBarError(it.message.toString()) } }
                .collect { quotes ->
                    if (!quotes.isNullOrEmpty()) {
                        setState { copy(favoriteState = FavoriteContract.FavoriteState.Success, quotes = quotes) }
                    } else {
                        setState { copy(favoriteState = FavoriteContract.FavoriteState.Empty) }
                    }
                }
        }
    }

    private fun deleteFavoriteQuote(quote: Quote) {
        viewModelScope.launch {
            deleteFavoriteQuoteUseCase(quote)
                .catch { setEffect { FavoriteContract.Effect.ShowSnackBarError(it.message.toString()) } }
                .collect { setEffect { FavoriteContract.Effect.ShowSnackBarDeleteQuote } }
        }
    }

    private fun deleteFavoriteQuotes() {
        viewModelScope.launch {
            if (uiState.value.quotes.isNotEmpty()) {
                deleteFavoriteQuotesUseCase()
                    .catch { setEffect { FavoriteContract.Effect.ShowSnackBarError(it.message.toString()) } }
                    .collect {
                        setState { copy(favoriteState = FavoriteContract.FavoriteState.Empty, quotes = listOf()) }
                        setEffect { FavoriteContract.Effect.ShowSnackBarDeleteQuotes }
                    }
            } else {
                setEffect { FavoriteContract.Effect.ShowSnackBarQuotesEmpty }
            }
        }
    }

    private fun updateQuotes(quotes: List<Quote>) {
        viewModelScope.launch {
            setState { copy(quotes = quotes) }
        }
    }

}