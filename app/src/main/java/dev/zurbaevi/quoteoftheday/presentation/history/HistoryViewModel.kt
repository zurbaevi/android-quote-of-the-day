package dev.zurbaevi.quoteoftheday.presentation.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuotesUseCase
import dev.zurbaevi.quoteoftheday.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
) : ViewModel() {

    private val _quotes = MutableLiveData<Resource<List<Quote>>>()
    val quotes: LiveData<Resource<List<Quote>>> get() = _quotes

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            _quotes.value = Resource.loading(null)
            try {
                _quotes.value = Resource.success(getQuotesUseCase())
            } catch (exception: Exception) {
                _quotes.value = Resource.error(null, exception.message ?: "Error Occurred!")
            }
        }
    }

}