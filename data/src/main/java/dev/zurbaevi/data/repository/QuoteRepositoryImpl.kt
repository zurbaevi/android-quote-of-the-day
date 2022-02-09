package dev.zurbaevi.data.repository

import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.data.local.datasource.LocalDataSource
import dev.zurbaevi.data.local.mapper.LocalMapper
import dev.zurbaevi.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.data.remote.mapper.NetworkMapper
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : QuoteRepository {

    override suspend fun getQuotes(): Flow<Resource<List<Quote>>> {
        return flow {
            try {
                emit(Resource.Success(localDataSource.getQuotes().map {
                    LocalMapper.map(it)
                }))
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }
    }

    override suspend fun getQuote(): Flow<Resource<Quote>> {
        return flow {
            try {
                emit(Resource.Success(NetworkMapper.map(remoteDataSource.getQuote())))
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }
    }

    override suspend fun insertQuote(quote: Quote): Flow<Resource<Long>> {
        return flow {
            try {
                emit((Resource.Success(localDataSource.insertQuote(LocalMapper.map(quote)))))
            } catch (exception: Exception) {
                emit(Resource.Error(exception))
            }
        }
    }

}