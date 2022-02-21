package dev.zurbaevi.quoteoftheday.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.base.BaseActivity
import dev.zurbaevi.quoteoftheday.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    override fun prepareView(savedInstanceState: Bundle?) {

    }

}
