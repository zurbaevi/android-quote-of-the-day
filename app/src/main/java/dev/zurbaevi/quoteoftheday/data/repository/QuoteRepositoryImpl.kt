package dev.zurbaevi.quoteoftheday.data.repository

import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapper
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapper
import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository

class QuoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkMapper: NetworkMapper,
    private val localMapper: LocalMapper
) : QuoteRepository {

    override suspend fun getQuote(): Quote {
        networkMapper.mapQuoteDtoToDomain(remoteDataSource.getQuote()).also {
            localDataSource.insertQuote(it)
            return localMapper.mapEntityQuoteToDomain(it)
        }
    }

    override suspend fun getQuotes(): List<Quote> {
        return localDataSource.getQuotes().map { localMapper.mapEntityQuoteToDomain(it) }
    }

}