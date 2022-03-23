package dev.zurbaevi.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_favorite_table")
data class FavoriteQuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val quoteId: Long,
    @ColumnInfo(name = "quote_author")
    val quoteAuthor: String,
    @ColumnInfo(name = "quote_text")
    val quoteText: String,
)
