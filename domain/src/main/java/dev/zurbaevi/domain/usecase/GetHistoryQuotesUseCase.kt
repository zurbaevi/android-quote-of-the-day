package dev.zurbaevi.domain.usecase

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetHistoryQuotesUseCase(
    private val historyRepository: HistoryRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<Quote>> {
        return historyRepository.getQuotes().flowOn(dispatcher)
    }

}