package dev.zurbaevi.domain.repository

import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun insertFavoriteQuote(quote: Quote): Flow<Unit>

    fun getFavoriteQuotes(): Flow<List<Quote>>

    fun checkFavoriteQuote(quote: Quote): Flow<Boolean>

    fun deleteFavoriteQuote(quote: Quote): Flow<Unit>

}