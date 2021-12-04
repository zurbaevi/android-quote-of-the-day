package dev.zurbaevi.quoteoftheday.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.zurbaevi.quoteoftheday.data.local.QuoteDao
import dev.zurbaevi.quoteoftheday.data.local.QuoteDatabase
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSource
import dev.zurbaevi.quoteoftheday.data.local.datasource.LocalDataSourceImpl
import dev.zurbaevi.quoteoftheday.data.local.mapper.LocalMapperImpl
import dev.zurbaevi.quoteoftheday.data.remote.QuoteApi
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSource
import dev.zurbaevi.quoteoftheday.data.remote.datasource.RemoteDataSourceImpl
import dev.zurbaevi.quoteoftheday.data.remote.mapper.NetworkMapperImpl
import dev.zurbaevi.quoteoftheday.data.repository.QuoteRepositoryImpl
import dev.zurbaevi.quoteoftheday.domain.repository.QuoteRepository
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuoteUseCase
import dev.zurbaevi.quoteoftheday.domain.usecase.GetQuotesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetQuoteUseCase(repository: QuoteRepository): GetQuoteUseCase {
        return GetQuoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(repository: QuoteRepository): GetQuotesUseCase {
        return GetQuotesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        networkMapperImpl: NetworkMapperImpl,
        localMapperImpl: LocalMapperImpl,
    ): QuoteRepository {
        return QuoteRepositoryImpl(
            remoteDataSource,
            localDataSource,
            networkMapperImpl,
            localMapperImpl,
        )
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(quoteApi: QuoteApi): RemoteDataSource =
        RemoteDataSourceImpl(quoteApi)

    @Provides
    @Singleton
    fun provideLocalDataSource(quoteDao: QuoteDao): LocalDataSource =
        LocalDataSourceImpl(quoteDao)

    @Provides
    @Singleton
    fun provideRetrofitInterface(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(QuoteApi.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideQuoteApi(retrofit: Retrofit): QuoteApi =
        retrofit.create(QuoteApi::class.java)

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        QuoteDatabase::class.java,
        "quote_database"
    ).build()

    @Singleton
    @Provides
    fun provideQuoteDao(database: QuoteDatabase) = database.quoteDao()

}