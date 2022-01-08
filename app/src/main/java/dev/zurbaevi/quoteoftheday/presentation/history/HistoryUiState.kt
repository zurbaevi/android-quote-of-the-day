package dev.zurbaevi.quoteoftheday.presentation.history

import dev.zurbaevi.quoteoftheday.domain.model.Quote

data class HistoryUiState(
    val isFetchingQuote: Boolean = false,
    val quotes: List<Quote> = listOf(),
    val error: String = ""
)