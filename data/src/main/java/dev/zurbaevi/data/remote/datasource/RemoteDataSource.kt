package dev.zurbaevi.data.remote.datasource

import dev.zurbaevi.data.remote.dto.QuoteDto

interface RemoteDataSource {

    suspend fun getQuote(): QuoteDto

}