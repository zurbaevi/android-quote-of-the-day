package dev.zurbaevi.data.local.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.domain.model.Quote
import javax.inject.Inject

class LocalMapperImpl @Inject constructor() : LocalMapper {

    override fun mapEntityQuoteToDomain(quoteEntity: QuoteEntity): Quote {
        return Quote(
            quoteAuthor = quoteEntity.quoteAuthor,
            quoteId = quoteEntity.quoteId,
            quoteText = quoteEntity.quoteText
        )
    }

    override fun mapDomainToEntityQuote(quote: Quote): QuoteEntity {
        return QuoteEntity(
            quoteAuthor = quote.quoteAuthor,
            quoteId = quote.quoteId,
            quoteText = quote.quoteText
        )
    }

}