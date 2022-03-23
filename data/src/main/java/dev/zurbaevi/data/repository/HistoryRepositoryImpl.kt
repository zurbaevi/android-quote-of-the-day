package dev.zurbaevi.data.repository

import dev.zurbaevi.common.mapper.LocalMapper
import dev.zurbaevi.data.local.data_source.history.HistoryLocalDataSource
import dev.zurbaevi.data.local.model.HistoryQuoteEntity
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyLocalDataSource: HistoryLocalDataSource,
    private val mapper: LocalMapper<Quote, HistoryQuoteEntity>
) : HistoryRepository {

    override fun getHistoryQuotes() = flow {
        emit(mapper.toList(historyLocalDataSource.getHistoryQuotes()))
    }

    override fun deleteHistoryQuotes() = flow {
        emit(historyLocalDataSource.deleteHistoryQuotes())
    }

    override fun insertHistoryQuote(quote: Quote) = flow {
        emit(historyLocalDataSource.insertHistoryQuote(mapper.from(quote)))
    }

}