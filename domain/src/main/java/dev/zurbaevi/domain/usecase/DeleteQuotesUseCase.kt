package dev.zurbaevi.domain.usecase

import dev.zurbaevi.domain.repository.QuoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DeleteQuotesUseCase(
    private val quoteRepository: QuoteRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<Unit> {
        return quoteRepository.deleteQuotes().flowOn(dispatcher)
    }

}