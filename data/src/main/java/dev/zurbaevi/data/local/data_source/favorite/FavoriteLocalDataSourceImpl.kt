package dev.zurbaevi.data.local.data_source.favorite

import dev.zurbaevi.data.local.dao.history.FavoriteDao
import dev.zurbaevi.data.local.model.FavoriteQuoteEntity
import javax.inject.Inject

class FavoriteLocalDataSourceImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteLocalDataSource {

    override suspend fun insertFavoriteQuote(quoteFavoriteQuoteEntity: FavoriteQuoteEntity) {
        favoriteDao.insertFavoriteQuote(quoteFavoriteQuoteEntity)
    }

    override suspend fun getFavoriteQuotes(): List<FavoriteQuoteEntity> {
        return favoriteDao.getFavoriteQuotes()
    }

    override suspend fun checkFavoriteQuote(favoriteQuoteEntity: FavoriteQuoteEntity): Boolean {
        return favoriteDao.checkFavoriteQuote(
            favoriteQuoteEntity.quoteText,
        )
    }

    override suspend fun deleteFavoriteQuote(favoriteQuoteEntity: FavoriteQuoteEntity) {
        favoriteDao.deleteFavoriteQuote(
            favoriteQuoteEntity.quoteText,
            favoriteQuoteEntity.quoteAuthor
        )
    }

    override suspend fun deleteFavoriteQuotes() {
        favoriteDao.deleteFavoriteQuotes()
    }

}