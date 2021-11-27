package dev.zurbaevi.quoteoftheday.domain.usecase

import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository

class GetQuotesUseCase(
    private val quoteRepository: QuoteRepository
) {
    suspend operator fun invoke(): List<Quote> {
        return quoteRepository.getQuotes()
    }
}