package dev.zurbaevi.quoteoftheday.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.zurbaevi.quoteoftheday.data.model.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

}