package dev.zurbaevi.quoteoftheday.domain.repository

import dev.zurbaevi.quoteoftheday.domain.model.Quote

interface QuoteRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun getQuote(): Quote

}