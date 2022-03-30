package dev.zurbaevi.data.repository

import dev.zurbaevi.common.mapper.NetworkMapper
import dev.zurbaevi.data.remote.data_source.HomeRemoteDataSource
import dev.zurbaevi.data.remote.model.QuoteDto
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HomeRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val mapper: NetworkMapper<Quote, QuoteDto>
) : HomeRepository {

    override fun fetchHomeQuote(language: String) = flow {
        emit(mapper.to(homeRemoteDataSource.fetchHomeQuote(language)))
    }

}