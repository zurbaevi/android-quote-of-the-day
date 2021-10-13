package dev.zurbaevi.quoteoftheday.data.api

import dev.zurbaevi.quoteoftheday.data.model.Quote
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("1.0/")
    @FormUrlEncoded
    suspend fun getQuote(
        @Field("method") method: String = "getQuote",
        @Field("format") format: String = "json",
        @Field("lang") lang: String = "ru",
    ): Quote

}