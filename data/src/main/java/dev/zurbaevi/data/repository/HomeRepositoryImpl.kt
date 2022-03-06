package dev.zurbaevi.data.repository

import dev.zurbaevi.data.remote.datasource.HomeRemoteDataSource
import dev.zurbaevi.data.remote.mapper.NetworkMapper
import dev.zurbaevi.domain.repository.HomeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    override fun getQuote() = flow {
        emit(NetworkMapper.map(homeRemoteDataSource.fetchQuote()))
    }

}
