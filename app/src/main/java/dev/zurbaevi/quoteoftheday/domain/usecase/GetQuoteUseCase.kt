package dev.zurbaevi.quoteoftheday.domain.usecase

import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRemoteRepository

class GetQuoteUseCase(
    private val quoteRemoteRepository: QuoteRemoteRepository
) {

    suspend operator fun invoke(): Quote {
        return quoteRemoteRepository.getQuote()
    }

}