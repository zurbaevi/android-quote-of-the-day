package dev.zurbaevi.domain.repository

import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotes(): Flow<List<Quote>>

    fun getQuote(): Flow<Quote>

    fun insertQuote(quote: Quote): Flow<Unit>

    fun deleteQuotes(): Flow<Unit>

}