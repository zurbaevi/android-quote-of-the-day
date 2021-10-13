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

class QuoteHistoryViewModel @Inject constructor(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    private val _quotes = MutableLiveData<Resource<List<Quote>>>()
    val quotes: LiveData<Resource<List<Quote>>> get() = _quotes

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            _quotes.value = Resource.loading(null)
            try {
                _quotes.postValue(Resource.success(quoteRepository.getQuotes()))
            } catch (exception: Exception) {
                _quotes.postValue(Resource.error(null, exception.message ?: "Error Occurred!"))
            }
        }
    }

}