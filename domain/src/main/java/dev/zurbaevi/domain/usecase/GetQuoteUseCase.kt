package dev.zurbaevi.domain.usecase

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.QuoteRepository

class GetQuoteUseCase(
    private val quoteRepository: QuoteRepository
) {

    suspend operator fun invoke(): Quote {
        return quoteRepository.getQuote()
    }

}