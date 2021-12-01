package dev.zurbaevi.quoteoftheday.data.local.datasource

import dev.zurbaevi.quoteoftheday.data.local.entity.QuoteEntity

interface LocalDataSource {

    suspend fun getQuotes(): List<QuoteEntity>
    suspend fun insertQuote(quoteEntity: QuoteEntity)

}