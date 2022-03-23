package dev.zurbaevi.data.local.dao.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.zurbaevi.data.local.model.HistoryQuoteEntity

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryQuote(historyQuote: HistoryQuoteEntity)

    @Query("select * from quote_history_table")
    suspend fun getHistoryQuotes(): List<HistoryQuoteEntity>

    @Query("delete from quote_history_table")
    suspend fun deleteHistoryQuotes()

}