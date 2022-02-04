package dev.zurbaevi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.zurbaevi.data.local.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {
        const val DATABASE_NAME = "quote_database"
    }

}