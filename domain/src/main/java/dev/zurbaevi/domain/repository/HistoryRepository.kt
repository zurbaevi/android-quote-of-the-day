package dev.zurbaevi.domain.repository

import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getQuotes(): Flow<List<Quote>>

    fun deleteQuotes(): Flow<Unit>

    fun insertQuote(quote: Quote): Flow<Unit>

    fun checkQuote(quoteAuthor: String, quoteText: String): Flow<Boolean>

}