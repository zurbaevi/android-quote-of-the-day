package dev.zurbaevi.domain.repository

import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotes(): Flow<Resource<List<Quote>>>

    fun getQuote(): Flow<Resource<Quote>>

    fun insertQuote(quote: Quote): Flow<Unit>

    fun deleteQuotes(): Flow<Unit>

}