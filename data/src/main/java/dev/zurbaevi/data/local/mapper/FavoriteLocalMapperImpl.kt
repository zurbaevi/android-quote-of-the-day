package dev.zurbaevi.data.local.mapper

import dev.zurbaevi.common.mapper.LocalMapper
import dev.zurbaevi.data.local.model.FavoriteQuoteEntity
import dev.zurbaevi.domain.model.Quote
import javax.inject.Inject

class FavoriteLocalMapperImpl @Inject constructor() : LocalMapper<Quote, FavoriteQuoteEntity> {

    override fun from(i: Quote?): FavoriteQuoteEntity {
        return FavoriteQuoteEntity(
            quoteAuthor = i?.quoteAuthor ?: "",
            quoteId = i?.quoteId ?: 0,
            quoteText = i?.quoteText ?: ""
        )
    }

    override fun to(o: FavoriteQuoteEntity?): Quote {
        return Quote(
            quoteAuthor = o?.quoteAuthor ?: "",
            quoteId = o?.quoteId ?: 0,
            quoteText = o?.quoteText ?: ""
        )
    }

}