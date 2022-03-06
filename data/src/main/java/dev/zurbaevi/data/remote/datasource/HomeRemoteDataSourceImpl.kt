package dev.zurbaevi.data.remote.datasource

import dev.zurbaevi.data.remote.ApiService
import dev.zurbaevi.data.remote.dto.QuoteDto
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRemoteDataSource {

    override suspend fun fetchQuote(): QuoteDto {
        return apiService.fetchQuote()
    }

}