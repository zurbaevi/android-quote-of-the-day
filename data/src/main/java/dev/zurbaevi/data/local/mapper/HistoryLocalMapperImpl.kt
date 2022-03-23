package dev.zurbaevi.data.local.mapper

import dev.zurbaevi.common.mapper.LocalMapper
import dev.zurbaevi.data.local.model.HistoryQuoteEntity
import dev.zurbaevi.domain.model.Quote
import javax.inject.Inject

class HistoryLocalMapperImpl @Inject constructor() : LocalMapper<Quote, HistoryQuoteEntity> {

    override fun from(i: Quote?): HistoryQuoteEntity {
        return HistoryQuoteEntity(
            quoteAuthor = i?.quoteAuthor ?: "",
            quoteId = i?.quoteId ?: 0,
            quoteText = i?.quoteText ?: ""
        )
    }

    override fun to(o: HistoryQuoteEntity?): Quote {
        return Quote(
            quoteAuthor = o?.quoteAuthor ?: "",
            quoteId = o?.quoteId ?: 0,
            quoteText = o?.quoteText ?: ""
        )
    }

}