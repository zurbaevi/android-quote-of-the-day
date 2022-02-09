package dev.zurbaevi.data.local.datasource

import dev.zurbaevi.data.local.QuoteDao
import dev.zurbaevi.data.local.entity.QuoteEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val quoteDao: QuoteDao
) : LocalDataSource {

    override suspend fun getQuotes(): List<QuoteEntity> {
        return quoteDao.getQuotes()
    }

    override suspend fun insertQuote(quoteEntity: QuoteEntity): Long {
        return quoteDao.insertQuote(quoteEntity)
    }

}