package dev.zurbaevi.domain.repository

import dev.zurbaevi.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getQuote(): Flow<Quote>

}