package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.quoteoftheday.data.local.QuoteDao
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSourceImpl
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapperImpl
import dev.zurbaevi.quoteoftheday.data.remote.QuoteApi
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSourceImpl
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapperImpl
import dev.zurbaevi.quoteoftheday.data.repository.QuoteRepositoryImpl
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuotesUseCase
import dev.zurbaevi.quoteoftheday.domain.usecase.InsertQuoteUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteRepositoryImpl(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        localMapperImpl: LocalMapperImpl,
        networkMapperImpl: NetworkMapperImpl
    ): QuoteRepository {
        return QuoteRepositoryImpl(
            remoteDataSource,
            localDataSource,
            networkMapperImpl,
            localMapperImpl
        )
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(quoteApi: QuoteApi): RemoteDataSource =
        RemoteDataSourceImpl(quoteApi)

    @Provides
    @Singleton
    fun provideLocalDataSource(quoteDao: QuoteDao): LocalDataSource =
        LocalDataSourceImpl(quoteDao)

    @Provides
    @Singleton
    fun provideGetQuoteUseCase(quoteRepository: QuoteRepository): GetQuoteUseCase {
        return GetQuoteUseCase(quoteRepository)
    }

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(quoteRepository: QuoteRepository): GetQuotesUseCase {
        return GetQuotesUseCase(quoteRepository)
    }

    @Provides
    @Singleton
    fun provideInsertQuoteUseCase(quoteRepository: QuoteRepository): InsertQuoteUseCase {
        return InsertQuoteUseCase(quoteRepository)
    }

}