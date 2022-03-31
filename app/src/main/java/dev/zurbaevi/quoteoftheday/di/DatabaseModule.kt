package dev.zurbaevi.quoteoftheday.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.data.local.data_store.SettingsDataStore
import dev.zurbaevi.data.local.data_store.SettingsDataStoreImpl
import dev.zurbaevi.data.local.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "database"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideHistoryDao(database: AppDatabase) = database.historyDao()

    @Singleton
    @Provides
    fun provideFavoriteDao(database: AppDatabase) = database.favoriteDao()

    @Provides
    fun provideSettingsDataStore(@ApplicationContext context: Context): SettingsDataStore {
        return SettingsDataStoreImpl(context)
    }

}