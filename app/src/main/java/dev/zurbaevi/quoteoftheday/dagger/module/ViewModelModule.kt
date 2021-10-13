package dev.zurbaevi.quoteoftheday.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.zurbaevi.quoteoftheday.dagger.factory.ViewModelFactory
import dev.zurbaevi.quoteoftheday.ui.viewmodel.QuoteHistoryViewModel
import dev.zurbaevi.quoteoftheday.ui.viewmodel.QuoteViewModel
import dev.zurbaevi.quoteoftheday.util.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuoteViewModel::class)
    internal abstract fun bindQuoteViewModel(viewModel: QuoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuoteHistoryViewModel::class)
    internal abstract fun bindQuoteHistoryViewModel(viewModel: QuoteHistoryViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
