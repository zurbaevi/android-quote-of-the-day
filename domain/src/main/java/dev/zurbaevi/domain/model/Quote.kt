package dev.zurbaevi.domain.model

data class Quote(
    val quoteId: Long,
    val quoteAuthor: String,
    val quoteText: String,
) {
    companion object {
        val EMPTY = Quote(0, "", "")
    }
}