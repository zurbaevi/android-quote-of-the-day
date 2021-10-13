package dev.zurbaevi.quoteoftheday.dagger.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.zurbaevi.quoteoftheday.data.database.QuoteDatabase
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideTaskDatabase() = Room.databaseBuilder(
        application.applicationContext,
        QuoteDatabase::class.java,
        "quote_database"
    ).build()

    @Singleton
    @Provides
    fun provideTaskDao(database: QuoteDatabase) = database.quoteDao()
}