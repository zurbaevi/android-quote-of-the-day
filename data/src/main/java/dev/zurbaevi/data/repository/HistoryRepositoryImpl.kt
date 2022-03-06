package dev.zurbaevi.data.repository

import dev.zurbaevi.data.local.datasource.HistoryLocalDataSource
import dev.zurbaevi.data.local.mapper.LocalMapper
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyLocalDataSource: HistoryLocalDataSource
) : HistoryRepository {

    override fun getQuotes() = flow {
        emit(historyLocalDataSource.getQuotes().map { quoteEntity ->
            LocalMapper.map(quoteEntity)
        })
    }

    override fun deleteQuotes() = flow {
        emit(historyLocalDataSource.deleteQuotes())
    }

    override fun insertQuote(quote: Quote) = flow {
        emit(historyLocalDataSource.insertQuote(LocalMapper.map(quote)))
    }

    override fun checkQuote(quoteAuthor: String, quoteText: String) = flow {
        emit(historyLocalDataSource.checkQuote(quoteAuthor, quoteText))
    }

}