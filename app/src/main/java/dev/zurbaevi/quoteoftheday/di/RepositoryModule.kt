package dev.zurbaevi.quoteoftheday.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.data.local.data_source.favorite.FavoriteLocalDataSource
import dev.zurbaevi.data.local.data_source.favorite.FavoriteLocalDataSourceImpl
import dev.zurbaevi.data.local.data_source.history.HistoryLocalDataSource
import dev.zurbaevi.data.local.data_source.history.HistoryLocalDataSourceImpl
import dev.zurbaevi.data.remote.data_source.HomeRemoteDataSource
import dev.zurbaevi.data.remote.data_source.HomeRemoteDataSourceImpl
import dev.zurbaevi.data.repository.FavoriteRepositoryImpl
import dev.zurbaevi.data.repository.HistoryRepositoryImpl
import dev.zurbaevi.data.repository.HomeRepositoryImpl
import dev.zurbaevi.data.repository.SettingsRepositoryImpl
import dev.zurbaevi.domain.repository.FavoriteRepository
import dev.zurbaevi.domain.repository.HistoryRepository
import dev.zurbaevi.domain.repository.HomeRepository
import dev.zurbaevi.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    abstract fun provideHistoryRepository(historyRepositoryImpl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    @Singleton
    abstract fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun provideFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    @Singleton
    abstract fun provideHomeRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideHistoryLocalDataSource(historyLocalDataSourceImpl: HistoryLocalDataSourceImpl): HistoryLocalDataSource

    @Binds
    @Singleton
    abstract fun provideFavoriteLocalDataSource(favoriteLocalDataSourceImpl: FavoriteLocalDataSourceImpl): FavoriteLocalDataSource

}