package dev.zurbaevi.data.remote.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.data.remote.dto.QuoteDto
import dev.zurbaevi.domain.model.Quote

object NetworkMapper {

    fun map(quoteDto: QuoteDto): Quote {
        return Quote(
            quoteAuthor = quoteDto.quoteAuthor ?: "",
            quoteId = quoteDto.quoteId ?: 0,
            quoteText = quoteDto.quoteText ?: ""
        )
    }

}