package dev.zurbaevi.data.local.data_source.history

import dev.zurbaevi.data.local.dao.history.HistoryDao
import dev.zurbaevi.data.local.model.HistoryQuoteEntity
import javax.inject.Inject

class HistoryLocalDataSourceImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryLocalDataSource {

    override suspend fun getHistoryQuotes(): List<HistoryQuoteEntity> {
        return historyDao.getHistoryQuotes()
    }

    override suspend fun insertHistoryQuote(historyQuoteEntity: HistoryQuoteEntity) {
        historyDao.insertHistoryQuote(historyQuoteEntity)
    }

    override suspend fun deleteHistoryQuotes() {
        historyDao.deleteHistoryQuotes()
    }

}