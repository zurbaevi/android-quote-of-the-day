package dev.zurbaevi.data.local.datasource

import dev.zurbaevi.data.local.HistoryDao
import dev.zurbaevi.data.local.entity.QuoteEntity
import javax.inject.Inject

class HistoryLocalDataSourceImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryLocalDataSource {

    override suspend fun getQuotes(): List<QuoteEntity> {
        return historyDao.getQuotes()
    }

    override suspend fun insertQuote(quoteEntity: QuoteEntity) {
        historyDao.insertQuote(quoteEntity)
    }

    override suspend fun deleteQuotes() {
        historyDao.deleteQuotes()
    }

    override suspend fun checkQuote(quoteAuthor: String, quoteText: String): Boolean {
        return historyDao.checkQuote(quoteAuthor, quoteText)
    }

}