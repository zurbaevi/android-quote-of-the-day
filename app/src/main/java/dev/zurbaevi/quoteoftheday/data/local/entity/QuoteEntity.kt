package dev.zurbaevi.quoteoftheday.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_quote")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val quoteId: Long,
    @ColumnInfo(name = "quote_author")
    val quoteAuthor: String,
    @ColumnInfo(name = "quote_text")
    val quoteText: String,
)