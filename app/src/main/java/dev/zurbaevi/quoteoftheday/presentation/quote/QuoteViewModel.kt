package dev.zurbaevi.quoteoftheday.presentation.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.quoteoftheday.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase,
) : ViewModel() {

    private val _quote = MutableLiveData<Resource<Quote>>()
    val quote: LiveData<Resource<Quote>> get() = _quote

    init {
        getQuote()
    }

    fun getQuote() {
        viewModelScope.launch {
            _quote.value = Resource.loading(null)
            try {
                _quote.value = Resource.success(getQuoteUseCase())
            } catch (exception: Exception) {
                _quote.value = Resource.error(
                    message = exception.message ?: "Something went wrong",
                    data = null
                )
            }
        }
    }

}