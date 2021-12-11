package dev.zurbaevi.quoteoftheday.data.remote.mapper

import dev.zurbaevi.quoteoftheday.data.local.entity.QuoteEntity
import dev.zurbaevi.quoteoftheday.data.remote.dto.QuoteDto
import javax.inject.Inject

class NetworkMapperImpl @Inject constructor() : NetworkMapper {

    override fun mapQuoteDtoToDomain(quoteDto: QuoteDto): QuoteEntity {
        return QuoteEntity(
            quoteAuthor = quoteDto.quoteAuthor ?: "",
            quoteId = quoteDto.quoteId ?: 0,
            quoteText = quoteDto.quoteText ?: ""
        )
    }

}