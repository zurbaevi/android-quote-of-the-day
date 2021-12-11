package dev.zurbaevi.quoteoftheday.data.remote.datasource

import dev.zurbaevi.quoteoftheday.data.remote.QuoteApi
import dev.zurbaevi.quoteoftheday.data.remote.dto.QuoteDto

class RemoteDataSourceImpl(
    private val quoteApi: QuoteApi
) : RemoteDataSource {

    override suspend fun getQuote(): QuoteDto {
        return quoteApi.getQuote()
    }

}