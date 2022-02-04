package dev.zurbaevi.data.remote.datasource

import dev.zurbaevi.data.remote.QuoteApi
import dev.zurbaevi.data.remote.dto.QuoteDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val quoteApi: QuoteApi
) : RemoteDataSource {

    override suspend fun getQuote(): QuoteDto {
        return quoteApi.getQuote()
    }

}