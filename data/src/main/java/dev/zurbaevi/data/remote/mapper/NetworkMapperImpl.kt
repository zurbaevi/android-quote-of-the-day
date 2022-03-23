package dev.zurbaevi.data.remote.mapper

import dev.zurbaevi.common.mapper.NetworkMapper
import dev.zurbaevi.data.remote.model.QuoteDto
import dev.zurbaevi.domain.model.Quote
import javax.inject.Inject

class NetworkMapperImpl @Inject constructor() : NetworkMapper<Quote, QuoteDto> {

    override fun from(i: Quote?): QuoteDto {
        return QuoteDto(
            quoteAuthor = i?.quoteAuthor ?: "",
            quoteId = i?.quoteId ?: 0,
            quoteText = i?.quoteText ?: ""
        )
    }

    override fun to(o: QuoteDto?): Quote {
        return Quote(
            quoteAuthor = o?.quoteAuthor ?: "",
            quoteId = o?.quoteId ?: 0,
            quoteText = o?.quoteText ?: ""
        )
    }

}