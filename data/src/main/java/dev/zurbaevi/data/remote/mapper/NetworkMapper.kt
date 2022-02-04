package dev.zurbaevi.data.remote.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.data.remote.dto.QuoteDto

interface NetworkMapper {

    fun mapQuoteDtoToDomain(quoteDto: QuoteDto): QuoteEntity

}