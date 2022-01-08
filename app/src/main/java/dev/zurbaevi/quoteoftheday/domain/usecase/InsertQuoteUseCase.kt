package dev.zurbaevi.quoteoftheday.domain.usecase

import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository

class InsertQuoteUseCase(
    private val quoteRepository: QuoteRepository
) {

    suspend operator fun invoke(quote: Quote) {
        quoteRepository.insertQuote(quote)
    }

}