package dev.zurbaevi.quoteoftheday.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSaveLanguageToDataStoreUseCase(settingsRepository: SettingsRepository): SaveLanguageToDataStoreUseCase {
        return SaveLanguageToDataStoreUseCase(settingsRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideGetLanguageFromDataStoreUseCase(settingsRepository: SettingsRepository): GetLanguageFromDataStoreUseCase {
        return GetLanguageFromDataStoreUseCase(settingsRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideFetchHomeQuoteUseCase(homeRepository: HomeRepository): FetchHomeQuoteUseCase {
        return FetchHomeQuoteUseCase(homeRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideGetHistoryQuotesUseCase(historyRepository: HistoryRepository): GetHistoryQuotesUseCase {
        return GetHistoryQuotesUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertHistoryQuoteUseCase(historyRepository: HistoryRepository): InsertHistoryQuoteUseCase {
        return InsertHistoryQuoteUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteHistoryQuotesUseCase(historyRepository: HistoryRepository): DeleteHistoryQuotesUseCase {
        return DeleteHistoryQuotesUseCase(historyRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideInsertFavoriteQuoteUseCase(favoriteRepository: FavoriteRepository): InsertFavoriteQuoteUseCase {
        return InsertFavoriteQuoteUseCase(favoriteRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideCheckFavoriteQuoteUseCase(favoriteRepository: FavoriteRepository): CheckFavoriteQuoteUseCase {
        return CheckFavoriteQuoteUseCase(favoriteRepository, Dispatchers.IO)
    }


    @Provides
    @ViewModelScoped
    fun provideDeleteFavoriteQuoteUseCase(favoriteRepository: FavoriteRepository): DeleteFavoriteQuoteUseCase {
        return DeleteFavoriteQuoteUseCase(favoriteRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteFavoriteQuotesUseCase(favoriteRepository: FavoriteRepository): DeleteFavoriteQuotesUseCase {
        return DeleteFavoriteQuotesUseCase(favoriteRepository, Dispatchers.IO)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFavoriteQuotesUseCase(favoriteRepository: FavoriteRepository): GetFavoriteQuotesUseCase {
        return GetFavoriteQuotesUseCase(favoriteRepository, Dispatchers.IO)
    }

}