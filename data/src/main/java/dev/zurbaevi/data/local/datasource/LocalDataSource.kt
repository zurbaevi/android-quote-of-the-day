package dev.zurbaevi.data.local.datasource

import dev.zurbaevi.data.local.entity.QuoteEntity

interface LocalDataSource {

    suspend fun getQuotes(): List<QuoteEntity>

    suspend fun insertQuote(quoteEntity: QuoteEntity): Long

    suspend fun deleteQuotes(): Int

}