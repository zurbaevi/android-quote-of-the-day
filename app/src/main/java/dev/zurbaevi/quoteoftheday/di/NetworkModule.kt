package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapperImpl
import dev.zurbaevi.quoteoftheday.data.remote.QuoteApi
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSourceImpl
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapperImpl
import dev.zurbaevi.quoteoftheday.data.repository.QuoteRemoteRepositoryImpl
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRemoteRepository
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuoteUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInterface(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(QuoteApi.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideQuoteApi(retrofit: Retrofit): QuoteApi =
        retrofit.create(QuoteApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(quoteApi: QuoteApi): RemoteDataSource =
        RemoteDataSourceImpl(quoteApi)

    @Provides
    @Singleton
    fun provideQuoteRemoteRepositoryImpl(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        networkMapperImpl: NetworkMapperImpl,
        localMapperImpl: LocalMapperImpl,
    ): QuoteRemoteRepository {
        return QuoteRemoteRepositoryImpl(
            remoteDataSource,
            localDataSource,
            networkMapperImpl,
            localMapperImpl,
        )
    }

    @Provides
    @Singleton
    fun provideGetQuoteUseCase(quoteRemoteRepository: QuoteRemoteRepository): GetQuoteUseCase {
        return GetQuoteUseCase(quoteRemoteRepository)
    }

}