package dev.zurbaevi.domain.repository

import dev.zurbaevi.domain.model.Quote

interface QuoteRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun getQuote(): Quote

    suspend fun insertQuote(quote: Quote)

}