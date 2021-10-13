package dev.zurbaevi.quoteoftheday.dagger.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dev.zurbaevi.quoteoftheday.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://api.forismatic.com/api/")
        .build()

    @Provides
    @Reusable
    internal fun provideMovieApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}