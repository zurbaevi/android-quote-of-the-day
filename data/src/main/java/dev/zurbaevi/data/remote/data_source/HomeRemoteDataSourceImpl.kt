package dev.zurbaevi.data.remote.data_source

import dev.zurbaevi.data.remote.api.ApiService
import dev.zurbaevi.data.remote.model.QuoteDto
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRemoteDataSource {

    override suspend fun fetchHomeQuote(language: String): QuoteDto {
        return apiService.fetchQuote(lang = language)
    }

}