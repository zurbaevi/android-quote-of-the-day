package dev.zurbaevi.quoteoftheday.presentation.quote

import dev.zurbaevi.quoteoftheday.domain.model.Quote

data class QuoteUiState(
    val isFetchingQuote: Boolean = false,
    val quote: Quote? = null,
    val error: String = ""
)