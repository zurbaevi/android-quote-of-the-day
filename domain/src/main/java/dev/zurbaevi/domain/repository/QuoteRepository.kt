package dev.zurbaevi.domain.repository

import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun getQuotes(): Flow<Resource<List<Quote>>>

    suspend fun getQuote(): Flow<Resource<Quote>>

    suspend fun insertQuote(quote: Quote): Flow<Resource<Long>>

}