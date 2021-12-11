package dev.zurbaevi.quoteoftheday.domain.repository

import dev.zurbaevi.quoteoftheday.domain.model.Quote

interface QuoteRemoteRepository {

    suspend fun getQuote(): Quote

}