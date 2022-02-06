package dev.zurbaevi.data.remote.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.data.remote.dto.QuoteDto

object NetworkMapper {

    fun map(quoteDto: QuoteDto): QuoteEntity {
        return QuoteEntity(
            quoteAuthor = quoteDto.quoteAuthor ?: "",
            quoteId = quoteDto.quoteId ?: 0,
            quoteText = quoteDto.quoteText ?: ""
        )
    }

}