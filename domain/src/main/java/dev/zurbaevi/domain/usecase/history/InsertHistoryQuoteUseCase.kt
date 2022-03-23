package dev.zurbaevi.domain.usecase.history

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HistoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class InsertHistoryQuoteUseCase(
    private val historyRepository: HistoryRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(quote: Quote): Flow<Unit> {
        return historyRepository.insertHistoryQuote(quote).flowOn(dispatcher)
    }

}