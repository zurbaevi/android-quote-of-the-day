package dev.zurbaevi.quoteoftheday.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zurbaevi.quoteoftheday.data.model.Quote
import dev.zurbaevi.quoteoftheday.data.repository.QuoteRepository
import dev.zurbaevi.quoteoftheday.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    private val _quote = MutableLiveData<Resource<Quote>>()
    val quote: LiveData<Resource<Quote>> get() = _quote

    init {
        getQuote()
    }

    fun getQuote() {
        viewModelScope.launch {
            _quote.value = Resource.loading(null)
            try {
                _quote.postValue(Resource.success(quoteRepository.getQuote()))
            } catch (exception: Exception) {
                _quote.postValue(Resource.error(null, exception.message ?: "Error Occurred!"))
            }
        }
    }

}