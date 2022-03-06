package dev.zurbaevi.data.remote.datasource

import dev.zurbaevi.data.remote.dto.QuoteDto

interface HomeRemoteDataSource {

    suspend fun fetchQuote(): QuoteDto

}