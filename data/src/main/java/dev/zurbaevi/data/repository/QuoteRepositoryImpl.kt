package dev.zurbaevi.data.repository

import dev.zurbaevi.data.local.datasource.LocalDataSource
import dev.zurbaevi.data.local.mapper.LocalMapper
import dev.zurbaevi.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.data.remote.mapper.NetworkMapper
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : QuoteRepository {

    override fun getQuotes() = flow {
        emit(localDataSource.getQuotes().map {
            LocalMapper.map(it)
        })
    }

    override fun getQuote() = flow {
        emit(NetworkMapper.map(remoteDataSource.getQuote()))
    }

    override fun insertQuote(quote: Quote) = flow {
        emit(localDataSource.insertQuote(LocalMapper.map(quote)))
    }

    override fun deleteQuotes() = flow {
        emit(localDataSource.deleteQuotes())
    }

}
