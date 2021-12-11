package dev.zurbaevi.quoteoftheday.domain.repository

import dev.zurbaevi.quoteoftheday.domain.model.Quote

interface QuoteLocalRepository {

    suspend fun getQuotes(): List<Quote>

}