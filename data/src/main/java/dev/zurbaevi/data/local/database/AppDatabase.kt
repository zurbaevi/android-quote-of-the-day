package dev.zurbaevi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.zurbaevi.data.local.dao.history.FavoriteDao
import dev.zurbaevi.data.local.dao.history.HistoryDao
import dev.zurbaevi.data.local.model.FavoriteQuoteEntity
import dev.zurbaevi.data.local.model.HistoryQuoteEntity

@Database(
    entities = [HistoryQuoteEntity::class, FavoriteQuoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    abstract fun favoriteDao(): FavoriteDao

}