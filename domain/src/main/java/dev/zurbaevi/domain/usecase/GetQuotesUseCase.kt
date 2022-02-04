package dev.zurbaevi.domain.usecase

import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.QuoteRepository

class GetQuotesUseCase(
    private val quoteRepository: QuoteRepository
) {

    suspend operator fun invoke(): List<Quote> {
        return quoteRepository.getQuotes()
    }

}