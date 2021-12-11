package dev.zurbaevi.quoteoftheday.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.quoteoftheday.databinding.ActivityQuoteBinding

@AndroidEntryPoint
class QuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}