package dev.zurbaevi.domain.usecase.favorite

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetFavoriteQuotesUseCase(
    private val favoriteRepository: FavoriteRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<Quote>> {
        return favoriteRepository.getFavoriteQuotes().flowOn(dispatcher)
    }

}