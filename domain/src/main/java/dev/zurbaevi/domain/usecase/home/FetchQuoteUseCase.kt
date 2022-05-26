package dev.zurbaevi.domain.usecase.home

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class FetchQuoteUseCase(
    private val homeRepository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(language: String): Flow<Quote> {
        return homeRepository.fetchHomeQuote(language).flowOn(dispatcher)
    }

}