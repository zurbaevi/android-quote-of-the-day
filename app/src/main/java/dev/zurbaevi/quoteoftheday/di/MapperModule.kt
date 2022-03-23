package dev.zurbaevi.quoteoftheday.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.common.mapper.LocalMapper
import dev.zurbaevi.common.mapper.NetworkMapper
import dev.zurbaevi.data.local.mapper.FavoriteLocalMapperImpl
import dev.zurbaevi.data.local.mapper.HistoryLocalMapperImpl
import dev.zurbaevi.data.local.model.FavoriteQuoteEntity
import dev.zurbaevi.data.local.model.HistoryQuoteEntity
import dev.zurbaevi.data.remote.mapper.NetworkMapperImpl
import dev.zurbaevi.data.remote.model.QuoteDto
import dev.zurbaevi.domain.model.Quote

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsHistoryLocalMapper(mapper: HistoryLocalMapperImpl): LocalMapper<Quote, HistoryQuoteEntity>

    @Binds
    abstract fun bindsFavoriteLocalMapper(mapper: FavoriteLocalMapperImpl): LocalMapper<Quote, FavoriteQuoteEntity>

    @Binds
    abstract fun bindsNetworkMapper(mapper: NetworkMapperImpl): NetworkMapper<Quote, QuoteDto>

}