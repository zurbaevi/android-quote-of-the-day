package dev.zurbaevi.domain.repository

import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getHistoryQuotes(): Flow<List<Quote>>

    fun deleteHistoryQuotes(): Flow<Unit>

    fun insertHistoryQuote(quote: Quote): Flow<Unit>

}