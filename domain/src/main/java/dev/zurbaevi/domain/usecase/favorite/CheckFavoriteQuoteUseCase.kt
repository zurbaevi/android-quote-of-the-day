package dev.zurbaevi.domain.usecase.favorite

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class CheckFavoriteQuoteUseCase(
    private val favoriteRepository: FavoriteRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(quote: Quote): Flow<Boolean> {
        return favoriteRepository.checkFavoriteQuote(quote).flowOn(dispatcher)
    }

}