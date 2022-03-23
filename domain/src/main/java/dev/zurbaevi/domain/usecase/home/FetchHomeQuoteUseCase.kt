package dev.zurbaevi.domain.usecase.home

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class FetchHomeQuoteUseCase(
    private val homeRepository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Quote> {
        return homeRepository.fetchHomeQuote().flowOn(dispatcher)
    }

}