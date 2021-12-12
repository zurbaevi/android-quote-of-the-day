package dev.zurbaevi.quoteoftheday.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.quoteoftheday.data.local.QuoteDao
import dev.zurbaevi.quoteoftheday.data.local.QuoteDatabase
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSourceImpl
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapperImpl
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapperImpl
import dev.zurbaevi.quoteoftheday.data.repository.QuoteRepositoryImpl
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuotesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        QuoteDatabase::class.java,
        QuoteDatabase.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideQuoteDao(database: QuoteDatabase) = database.quoteDao()

}