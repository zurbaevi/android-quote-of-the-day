package dev.zurbaevi.quoteoftheday.domain.usecase

import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteLocalRepository

class GetQuotesUseCase(
    private val quoteLocalRepository: QuoteLocalRepository
) {

    suspend operator fun invoke(): List<Quote> {
        return quoteLocalRepository.getQuotes()
    }

}