package dev.zurbaevi.presentation.quote

import dev.zurbaevi.domain.model.Quote

data class QuoteUiState(
    val isFetchingQuote: Boolean = false,
    val quote: Quote? = null,
    val error: String = ""
)