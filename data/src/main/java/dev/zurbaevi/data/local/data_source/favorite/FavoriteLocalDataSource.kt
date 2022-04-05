package dev.zurbaevi.data.local.data_source.favorite

import dev.zurbaevi.data.local.model.FavoriteQuoteEntity

interface FavoriteLocalDataSource {

    suspend fun insertFavoriteQuote(quoteFavoriteQuoteEntity: FavoriteQuoteEntity)

    suspend fun getFavoriteQuotes(): List<FavoriteQuoteEntity>

    suspend fun checkFavoriteQuote(favoriteQuoteEntity: FavoriteQuoteEntity): Boolean

    suspend fun deleteFavoriteQuote(favoriteQuoteEntity: FavoriteQuoteEntity)

    suspend fun deleteFavoriteQuotes()

}