package dev.zurbaevi.data.remote.data_source

import dev.zurbaevi.data.remote.model.QuoteDto

interface HomeRemoteDataSource {

    suspend fun fetchHomeQuote(language: String): QuoteDto

}