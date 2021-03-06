package dev.zurbaevi.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.common.base.BaseViewModel
import dev.zurbaevi.common.base.UiText
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.usecase.favorite.CheckFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.favorite.DeleteFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.favorite.InsertFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.history.InsertHistoryQuoteUseCase
import dev.zurbaevi.domain.usecase.home.FetchQuoteUseCase
import dev.zurbaevi.domain.usecase.settings.GetLanguageFromDataStoreUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchQuoteUseCase: FetchQuoteUseCase,
    private val insertHistoryQuoteUseCase: InsertHistoryQuoteUseCase,
    private val insertFavoriteQuoteUseCase: InsertFavoriteQuoteUseCase,
    private val deleteFavoriteQuoteUseCase: DeleteFavoriteQuoteUseCase,
    private val checkFavoriteQuoteUseCase: CheckFavoriteQuoteUseCase,
    private val getLanguageFromDataStoreUseCase: GetLanguageFromDataStoreUseCase,
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(
            homeState = HomeContract.HomeState.Idle,
            quoteIsFavorite = false,
            quote = Quote.EMPTY
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnFetchQuote -> fetchQuote()
            is HomeContract.Event.OnInsertFavoriteQuote -> insertFavoriteQuote(uiState.value.quote)
            is HomeContract.Event.OnDeleteFavoriteQuote -> deleteFavoriteQuote(uiState.value.quote)
            is HomeContract.Event.OnCheckFavoriteQuote -> checkFavoriteQuote(uiState.value.quote)
        }
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            getLanguageFromDataStore { currentLanguage ->
                fetchQuoteUseCase(currentLanguage)
                    .onStart { setState { copy(homeState = HomeContract.HomeState.Loading) } }
                    .catch { setStateError(it.message.toString()) }
                    .collect { quote ->
                        setState { copy(homeState = HomeContract.HomeState.Success, quote = quote) }
                        checkFavoriteQuote(quote)
                        insertHistoryQuote(quote)
                    }
            }
        }
    }

    private fun insertHistoryQuote(quote: Quote) {
        viewModelScope.launch {
            insertHistoryQuoteUseCase(quote)
                .catch { setStateError(it.message.toString()) }
                .collect()
        }
    }

    private fun checkFavoriteQuote(quote: Quote) {
        viewModelScope.launch {
            checkFavoriteQuoteUseCase(quote)
                .catch { setStateError(it.message.toString()) }
                .collect { isFavorite ->
                    setState { copy(quoteIsFavorite = isFavorite) }
                }
        }
    }

    private fun insertFavoriteQuote(quote: Quote) {
        viewModelScope.launch {
            insertFavoriteQuoteUseCase(quote)
                .catch { setStateError(it.message.toString()) }
                .collect { setState { copy(quoteIsFavorite = true) } }
        }
    }

    private fun deleteFavoriteQuote(quote: Quote) {
        viewModelScope.launch {
            deleteFavoriteQuoteUseCase(quote)
                .catch { setStateError(it.message.toString()) }
                .collect { setState { copy(quoteIsFavorite = false) } }
        }
    }

    private fun getLanguageFromDataStore(action: suspend (String) -> Unit) {
        viewModelScope.launch {
            getLanguageFromDataStoreUseCase()
                .catch { setStateError(it.message.toString()) }
                .collect { action(it) }
        }
    }

    private fun setStateError(message: String) {
        setState { copy(homeState = HomeContract.HomeState.Error) }
        setEffect { HomeContract.Effect.ShowSnackBar(UiText.DynamicString(message)) }
    }

}