package dev.zurbaevi.domain.usecase

import dev.zurbaevi.common.util.Resource
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.QuoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class InsertQuoteUseCase(
    private val quoteRepository: QuoteRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(quote: Quote): Flow<Resource<Long>> {
        return quoteRepository.insertQuote(quote).flowOn(dispatcher)
    }

}