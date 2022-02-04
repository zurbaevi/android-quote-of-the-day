package dev.zurbaevi.data.remote.mapper

import dev.zurbaevi.data.local.entity.QuoteEntity
import dev.zurbaevi.data.remote.dto.QuoteDto
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