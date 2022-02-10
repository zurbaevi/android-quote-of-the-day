package dev.zurbaevi.data.local.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.domain.model.Quote

object LocalMapper {

    fun map(quoteEntity: QuoteEntity): Quote {
        return Quote(
            quoteAuthor = quoteEntity.quoteAuthor,
            quoteId = quoteEntity.quoteId,
            quoteText = quoteEntity.quoteText
        )
    }

    fun map(quote: Quote): QuoteEntity {
        return QuoteEntity(
            quoteAuthor = quote.quoteAuthor,
            quoteId = quote.quoteId,
            quoteText = quote.quoteText
        )
    }

}