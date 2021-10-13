package dev.zurbaevi.quoteoftheday.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.zurbaevi.quoteoftheday.data.model.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: Quote)

    @Query("select * from table_quote")
    suspend fun getQuotes(): List<Quote>

}