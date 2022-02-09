package dev.zurbaevi.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.base.BaseActivity
import dev.zurbaevi.presentation.R
import dev.zurbaevi.presentation.databinding.ActivityQuoteBinding

@AndroidEntryPoint
class QuoteActivity : BaseActivity<ActivityQuoteBinding>() {

    private lateinit var navController: NavController

    override val bindLayout: (LayoutInflater) -> ActivityQuoteBinding
        get() = ActivityQuoteBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
