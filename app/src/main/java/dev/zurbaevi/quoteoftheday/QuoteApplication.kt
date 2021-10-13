package dev.zurbaevi.quoteoftheday

import android.app.Application
import dev.zurbaevi.quoteoftheday.dagger.component.AppComponent
import dev.zurbaevi.quoteoftheday.dagger.component.DaggerAppComponent
import dev.zurbaevi.quoteoftheday.dagger.module.DatabaseModule

class QuoteApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }

}