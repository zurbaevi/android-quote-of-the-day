package dev.zurbaevi.quoteoftheday.dagger.component

import dagger.Component
import dev.zurbaevi.quoteoftheday.dagger.module.DatabaseModule
import dev.zurbaevi.quoteoftheday.dagger.module.NetworkModule
import dev.zurbaevi.quoteoftheday.dagger.module.ViewModelModule
import dev.zurbaevi.quoteoftheday.ui.view.fragment.QuoteFragment
import dev.zurbaevi.quoteoftheday.ui.view.fragment.QuoteHistoryFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(quoteFragment: QuoteFragment)
    fun inject(quoteHistoryFragment: QuoteHistoryFragment)

}