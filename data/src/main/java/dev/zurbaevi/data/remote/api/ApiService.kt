package dev.zurbaevi.data.remote.api

import dev.zurbaevi.data.remote.model.QuoteDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("1.0/")
    @FormUrlEncoded
    suspend fun fetchQuote(
        @Field("method") method: String = "getQuote",
        @Field("format") format: String = "json",
        @Field("lang") lang: String,
    ): QuoteDto

    companion object {
        const val BASE_URL = "http://api.forismatic.com/api/"
    }

}