package dev.zurbaevi.data.local.dao.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.zurbaevi.data.local.model.FavoriteQuoteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteQuote(quoteFavoriteQuoteEntity: FavoriteQuoteEntity)

    @Query("select * from quote_favorite_table")
    suspend fun getFavoriteQuotes(): List<FavoriteQuoteEntity>

    @Query("select count(*) from quote_favorite_table where quote_text = :quoteText and quote_author = :quoteAuthor like 0")
    suspend fun checkFavoriteQuote(quoteText: String, quoteAuthor: String): Boolean

    @Query("delete from quote_favorite_table where quote_text = :quoteText and quote_author = :quoteAuthor")
    suspend fun deleteFavoriteQuote(quoteText: String, quoteAuthor: String)

}