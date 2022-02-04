package dev.zurbaevi.data.repository

import dev.zurbaevi.data.local.datasource.LocalDataSource
import dev.zurbaevi.data.local.mapper.LocalMapper
import dev.zurbaevi.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.data.remote.mapper.NetworkMapper
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkMapper: NetworkMapper,
    private val localMapper: LocalMapper,
) : QuoteRepository {

    override suspend fun getQuotes(): List<Quote> {
        return withContext(Dispatchers.IO) {
            localDataSource.getQuotes().map { localMapper.mapEntityQuoteToDomain(it) }
        }
    }

    override suspend fun getQuote(): Quote {
        return withContext(Dispatchers.IO) {
            networkMapper.mapQuoteDtoToDomain(remoteDataSource.getQuote()).let {
                localMapper.mapEntityQuoteToDomain(it)
            }
        }
    }

    override suspend fun insertQuote(quote: Quote) {
        localDataSource.insertQuote(localMapper.mapDomainToEntityQuote(quote))
    }

}