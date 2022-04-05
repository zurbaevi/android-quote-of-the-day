package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.domain.repository.FavoriteRepository
import dev.zurbaevi.domain.repository.HistoryRepository
import dev.zurbaevi.domain.repository.HomeRepository
import dev.zurbaevi.domain.repository.SettingsRepository
import dev.zurbaevi.domain.usecase.favorite.*
import dev.zurbaevi.domain.usecase.history.DeleteHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.history.GetHistoryQuotesUseCase
import dev.zurbaevi.domain.usecase.history.InsertHistoryQuoteUseCase
import dev.zurbaevi.domain.usecase.home.FetchHomeQuoteUseCase
import dev.zurbaevi.domain.usecase.settings.GetLanguageFromDataStoreUseCase
import dev.zurbaevi.domain.usecase.settings.SaveLanguageToDataStoreUseCase
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSaveLanguageToDataStoreUseCase(settingsRepository: SettingsRepository): SaveLanguageToDataStoreUseCase {
        return SaveLanguageToDataStoreUseCase(settingsRepository, Dispatchers.IO)
    }

    @Provides
    fun provideGetLanguageFromDataStoreUseCase(settingsRepository: SettingsRepository): GetLanguageFromDataStoreUseCase {
        return GetLanguageFromDataStoreUseCase(settingsRepository, Dispatchers.IO)
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
    fun provideDeleteFavoriteQuotesUseCase(favoriteRepository: FavoriteRepository): DeleteFavoriteQuotesUseCase {
        return DeleteFavoriteQuotesUseCase(favoriteRepository, Dispatchers.IO)
    }

    @Provides
    fun provideGetFavoriteQuotesUseCase(favoriteRepository: FavoriteRepository): GetFavoriteQuotesUseCase {
        return GetFavoriteQuotesUseCase(favoriteRepository, Dispatchers.IO)
    }

}