package dev.zurbaevi.quoteoftheday.data.remote.mapper

import dev.zurbaevi.quoteoftheday.data.local.entity.QuoteEntity
import dev.zurbaevi.quoteoftheday.data.remote.dto.QuoteDto

interface NetworkMapper {

    fun mapQuoteDtoToDomain(quoteDto: QuoteDto): QuoteEntity

}