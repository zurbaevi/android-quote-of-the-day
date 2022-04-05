package dev.zurbaevi.data.repository

import dev.zurbaevi.common.mapper.LocalMapper
import dev.zurbaevi.data.local.data_source.favorite.FavoriteLocalDataSource
import dev.zurbaevi.data.local.model.FavoriteQuoteEntity
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
    private val mapper: LocalMapper<Quote, FavoriteQuoteEntity>
) : FavoriteRepository {

    override fun insertFavoriteQuote(quote: Quote) = flow {
        emit(favoriteLocalDataSource.insertFavoriteQuote(mapper.from(quote)))
    }

    override fun getFavoriteQuotes() = flow {
        emit(mapper.toList(favoriteLocalDataSource.getFavoriteQuotes()))
    }

    override fun checkFavoriteQuote(quote: Quote) = flow {
        emit(favoriteLocalDataSource.checkFavoriteQuote(mapper.from(quote)))
    }

    override fun deleteFavoriteQuote(quote: Quote) = flow {
        emit(favoriteLocalDataSource.deleteFavoriteQuote(mapper.from(quote)))
    }

    override fun deleteFavoriteQuotes() = flow {
        emit(favoriteLocalDataSource.deleteFavoriteQuotes())
    }

}