package dev.zurbaevi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.zurbaevi.data.local.entity.QuoteEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity): Long

    @Query("select * from table_quote")
    suspend fun getQuotes(): List<QuoteEntity>

}