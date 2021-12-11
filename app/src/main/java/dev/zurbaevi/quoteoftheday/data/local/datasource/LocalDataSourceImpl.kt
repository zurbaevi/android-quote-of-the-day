package dev.zurbaevi.quoteoftheday.data.local.datasource

import dev.zurbaevi.quoteoftheday.data.local.QuoteDao
import dev.zurbaevi.quoteoftheday.data.local.entity.QuoteEntity

class LocalDataSourceImpl(
    private val quoteDao: QuoteDao
) : LocalDataSource {

    override suspend fun getQuotes(): List<QuoteEntity> {
        return quoteDao.getQuotes()
    }

    override suspend fun insertQuote(quoteEntity: QuoteEntity) {
        quoteDao.insertQuote(quoteEntity)
    }

}