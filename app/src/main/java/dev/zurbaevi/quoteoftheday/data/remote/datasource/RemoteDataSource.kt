package dev.zurbaevi.quoteoftheday.data.remote.datasource

import dev.zurbaevi.quoteoftheday.data.remote.dto.QuoteDto

interface RemoteDataSource {

    suspend fun getQuote(): QuoteDto

}