package dev.zurbaevi.data.local.data_source.history

import dev.zurbaevi.data.local.model.HistoryQuoteEntity

interface HistoryLocalDataSource {

    suspend fun getHistoryQuotes(): List<HistoryQuoteEntity>

    suspend fun insertHistoryQuote(historyQuoteEntity: HistoryQuoteEntity)

    suspend fun deleteHistoryQuotes()

}