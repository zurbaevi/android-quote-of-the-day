package dev.zurbaevi.quoteoftheday.data.repository

import dev.zurbaevi.quoteoftheday.data.local.QuoteDao
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapper
import dev.zurbaevi.quoteoftheday.data.remote.QuoteApi
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapper
import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository

class QuoteRepositoryImpl(
    private val quoteApi: QuoteApi,
    private val quoteDao: QuoteDao,
    private val networkMapper: NetworkMapper,
    private val localMapper: LocalMapper
) : QuoteRepository {

    override suspend fun getQuote(): Quote {
        networkMapper.mapQuoteDtoToDomain(quoteApi.getQuote()).also {
            quoteDao.insertQuote(it)
            return localMapper.mapEntityQuoteToDomain(it)
        }
    }

    override suspend fun getQuotes(): List<Quote> {
        return quoteDao.getQuotes().map { localMapper.mapEntityQuoteToDomain(it) }
    }
}