package dev.zurbaevi.quoteoftheday.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.domain.repository.FavoriteRepository
import dev.zurbaevi.domain.repository.HistoryRepository
import dev.zurbaevi.domain.repository.HomeRepository
import dev.zurbaevi.domain.usecase.favorite.CheckFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.favorite.DeleteFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.favorite.GetFavoriteQuotesUseCase
import dev.zurbaevi.domain.usecase.favorite.InsertFavoriteQuoteUseCase
import dev.zurbaevi.domain.usecase.history.DeleteHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.history.GetHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.history.InsertHistoryQuoteUseCase
import dev.zurbaevi.domain.usecase.home.FetchHomeQuoteUseCase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSettingsDataStore(@ApplicationContext context: Context): Context {
        return context.applicationContext
    }

    @Provides
    fun provideFetchHomeQuoteUseCase(homeRepository: HomeRepository): FetchHomeQuoteUseCase {
        return FetchHomeQuoteUseCase(homeRepository, Dispatchers.IO)
    }

    @Provides
    fun provideGetHistoryQuotesUseCase(historyRepository: HistoryRepository): GetHistoryQuotesUseCase {
        return GetHistoryQuotesUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    fun provideInsertHistoryQuoteUseCase(historyRepository: HistoryRepository): InsertHistoryQuoteUseCase {
        return InsertHistoryQuoteUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    fun provideDeleteHistoryQuotesUseCase(historyRepository: HistoryRepository): DeleteHistoryQuotesUseCase {
        return DeleteHistoryQuotesUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    fun provideInsertFavoriteQuoteUseCase(favoriteRepository: FavoriteRepository): InsertFavoriteQuoteUseCase {
        return InsertFavoriteQuoteUseCase(favoriteRepository, Dispatchers.IO)
    }

    @Provides
    fun provideCheckFavoriteQuoteUseCase(favoriteRepository: FavoriteRepository): CheckFavoriteQuoteUseCase {
        return CheckFavoriteQuoteUseCase(favoriteRepository, Dispatchers.IO)
    }


    @Provides
    fun provideDeleteFavoriteQuoteUseCase(favoriteRepository: FavoriteRepository): DeleteFavoriteQuoteUseCase {
        return DeleteFavoriteQuoteUseCase(favoriteRepository, Dispatchers.IO)
    }


    @Provides
    fun provideGetFavoriteQuotesUseCase(favoriteRepository: FavoriteRepository): GetFavoriteQuotesUseCase {
        return GetFavoriteQuotesUseCase(favoriteRepository, Dispatchers.IO)
    }

}