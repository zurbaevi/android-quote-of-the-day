package dev.zurbaevi.data.local.datasource

import dev.zurbaevi.data.local.entity.QuoteEntity

interface HistoryLocalDataSource {

    suspend fun getQuotes(): List<QuoteEntity>

    suspend fun insertQuote(quoteEntity: QuoteEntity)

    suspend fun deleteQuotes()

    suspend fun checkQuote(quoteAuthor: String, quoteText: String): Boolean

}