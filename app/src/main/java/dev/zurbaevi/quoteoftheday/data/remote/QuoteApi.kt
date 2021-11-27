package dev.zurbaevi.quoteoftheday.data.remote

import dev.zurbaevi.quoteoftheday.data.remote.dto.QuoteDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface QuoteApi {

    @POST("1.0/")
    @FormUrlEncoded
    suspend fun getQuote(
        @Field("method") method: String = "getQuote",
        @Field("format") format: String = "json",
        @Field("lang") lang: String = "ru",
    ): QuoteDto

    companion object {
        const val BASE_URL = "http://api.forismatic.com/api/"
    }

}