package dev.zurbaevi.presentation.history

import dev.zurbaevi.domain.model.Quote

data class HistoryUiState(
    val isFetchingQuote: Boolean = false,
    val quotes: List<Quote> = listOf(),
    val error: String = ""
)