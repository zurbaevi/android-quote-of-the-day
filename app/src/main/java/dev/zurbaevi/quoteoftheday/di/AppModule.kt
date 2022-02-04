package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.data.local.datasource.LocalDataSourceImpl
import dev.zurbaevi.data.local.mapper.LocalMapperImpl
import dev.zurbaevi.data.remote.datasource.RemoteDataSourceImpl
import dev.zurbaevi.data.remote.mapper.NetworkMapperImpl
import dev.zurbaevi.data.repository.QuoteRepositoryImpl
import dev.zurbaevi.domain.repository.QuoteRepository
import dev.zurbaevi.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.domain.usecase.GetQuotesUseCase
import dev.zurbaevi.domain.usecase.InsertQuoteUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteRepositoryImpl(
        localDataSourceImpl: LocalDataSourceImpl,
        remoteDataSourceImpl: RemoteDataSourceImpl,
        localMapperImpl: LocalMapperImpl,
        networkMapperImpl: NetworkMapperImpl
    ): QuoteRepository {
        return QuoteRepositoryImpl(
            remoteDataSourceImpl,
            localDataSourceImpl,
            networkMapperImpl,
            localMapperImpl
        )
    }

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