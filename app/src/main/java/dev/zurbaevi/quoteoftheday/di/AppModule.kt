package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.data.local.datasource.HistoryLocalDataSourceImpl
import dev.zurbaevi.data.remote.datasource.HomeRemoteDataSourceImpl
import dev.zurbaevi.data.repository.HistoryRepositoryImpl
import dev.zurbaevi.data.repository.HomeRepositoryImpl
import dev.zurbaevi.domain.repository.HistoryRepository
import dev.zurbaevi.domain.repository.HomeRepository
import dev.zurbaevi.domain.usecase.DeleteHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.FetchQuoteUseCase
import dev.zurbaevi.domain.usecase.GetHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.InsertHistoryQuoteUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHomeRepositoryImpl(
        homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl,
    ): HomeRepository {
        return HomeRepositoryImpl(
            homeRemoteDataSourceImpl,
        )
    }

    @Provides
    @Singleton
    fun provideHistoryRepositoryImpl(
        historyLocalDataSourceImpl: HistoryLocalDataSourceImpl,
    ): HistoryRepository {
        return HistoryRepositoryImpl(
            historyLocalDataSourceImpl
        )
    }

    @Provides
    @Singleton
    fun provideGetQuoteUseCase(homeRepository: HomeRepository): FetchQuoteUseCase {
        return FetchQuoteUseCase(homeRepository, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(historyRepository: HistoryRepository): GetHistoryQuotesUseCase {
        return GetHistoryQuotesUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideInsertQuoteUseCase(historyRepository: HistoryRepository): InsertHistoryQuoteUseCase {
        return InsertHistoryQuoteUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideDeleteQuotesUseCase(historyRepository: HistoryRepository): DeleteHistoryQuotesUseCase {
        return DeleteHistoryQuotesUseCase(historyRepository, Dispatchers.IO)
    }

}