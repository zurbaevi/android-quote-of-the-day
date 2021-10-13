package dev.zurbaevi.quoteoftheday.data.repository

import dev.zurbaevi.quoteoftheday.data.api.ApiService
import dev.zurbaevi.quoteoftheday.data.database.QuoteDao
import dev.zurbaevi.quoteoftheday.data.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val apiService: ApiService,
    private val quoteDao: QuoteDao,
) {

    suspend fun getQuote() = withContext(Dispatchers.IO) {
        val quote = apiService.getQuote()
        insert(quote)
        quote
    }

    suspend fun getQuotes() = withContext(Dispatchers.IO) {
        quoteDao.getQuotes()
    }

    private suspend fun insert(quote: Quote) = withContext(Dispatchers.IO) {
        quoteDao.insert(quote)
    }

}