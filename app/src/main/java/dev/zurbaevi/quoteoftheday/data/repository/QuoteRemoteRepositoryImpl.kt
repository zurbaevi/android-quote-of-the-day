package dev.zurbaevi.quoteoftheday.data.repository

import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapper
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapper
import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteRemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkMapper: NetworkMapper,
    private val localMapper: LocalMapper,
) : QuoteRemoteRepository {

    override suspend fun getQuote(): Quote {
        return withContext(Dispatchers.IO) {
            networkMapper.mapQuoteDtoToDomain(remoteDataSource.getQuote()).let {
                localDataSource.insertQuote(it)
                localMapper.mapEntityQuoteToDomain(it)
            }
        }
    }

}