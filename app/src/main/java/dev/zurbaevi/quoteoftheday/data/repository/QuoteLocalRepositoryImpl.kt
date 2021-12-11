package dev.zurbaevi.quoteoftheday.data.repository

import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapper
import dev.zurbaevi.quoteoftheday.domain.model.Quote
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteLocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val localMapper: LocalMapper,
) : QuoteLocalRepository {

    override suspend fun getQuotes(): List<Quote> {
        return withContext(Dispatchers.IO) {
            localDataSource.getQuotes().map { localMapper.mapEntityQuoteToDomain(it) }
        }
    }

}