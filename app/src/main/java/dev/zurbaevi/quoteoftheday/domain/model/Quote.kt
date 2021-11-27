package dev.zurbaevi.quoteoftheday.domain.model

data class Quote(
    val quoteId: Long,
    val quoteAuthor: String,
    val quoteText: String,
)