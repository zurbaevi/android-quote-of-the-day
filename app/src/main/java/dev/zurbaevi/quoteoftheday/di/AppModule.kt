package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.data.local.datasource.LocalDataSourceImpl
import dev.zurbaevi.data.remote.datasource.RemoteDataSourceImpl
import dev.zurbaevi.data.repository.QuoteRepositoryImpl
import dev.zurbaevi.domain.repository.QuoteRepository
import dev.zurbaevi.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.domain.usecase.GetQuotesUseCase
import dev.zurbaevi.domain.usecase.InsertQuoteUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteRepositoryImpl(
        localDataSourceImpl: LocalDataSourceImpl,
        remoteDataSourceImpl: RemoteDataSourceImpl,
    ): QuoteRepository {
        return QuoteRepositoryImpl(
            remoteDataSourceImpl,
            localDataSourceImpl,
        )
    }

    @Provides
    @Singleton
    fun provideGetQuoteUseCase(quoteRepository: QuoteRepository): GetQuoteUseCase {
        return GetQuoteUseCase(quoteRepository, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(quoteRepository: QuoteRepository): GetQuotesUseCase {
        return GetQuotesUseCase(quoteRepository, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideInsertQuoteUseCase(quoteRepository: QuoteRepository): InsertQuoteUseCase {
        return InsertQuoteUseCase(quoteRepository, Dispatchers.IO)
    }

}