package dev.zurbaevi.quoteoftheday.data.local.mapper

import dev.zurbaevi.quoteoftheday.data.local.entity.QuoteEntity
import dev.zurbaevi.quoteoftheday.domain.model.Quote

interface LocalMapper {

    fun mapEntityQuoteToDomain(quoteEntity: QuoteEntity): Quote

    fun mapDomainToEntityQuote(quote: Quote): QuoteEntity

}