package dev.zurbaevi.data.local.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.domain.model.Quote

interface LocalMapper {

    fun mapEntityQuoteToDomain(quoteEntity: QuoteEntity): Quote

    fun mapDomainToEntityQuote(quote: Quote): QuoteEntity

}