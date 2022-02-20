package dev.zurbaevi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.base.BaseFragment
import dev.zurbaevi.common.util.exentsion.gone
import dev.zurbaevi.common.util.exentsion.setOnClickListenerWithDebounce
import dev.zurbaevi.common.util.exentsion.visible
import dev.zurbaevi.home.databinding.FragmentQuoteBinding

@AndroidEntryPoint
class QuoteFragment : BaseFragment<FragmentQuoteBinding>() {

    private val quoteViewModel by viewModels<QuoteViewModel>()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuoteBinding
        get() = FragmentQuoteBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initObservers()
        initListeners()
        if (quoteViewModel.currentState.quoteState is QuoteContract.QuoteState.Idle) {
            quoteViewModel.setEvent(QuoteContract.Event.FetchQuote)
        }
    }

    private fun initObservers() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                quoteViewModel.uiState.collect {
                    when (val state = it.quoteState) {
                        is QuoteContract.QuoteState.Idle -> {
                            progressBar.gone()
                            textViewText.gone()
                            textViewAuthor.gone()
                        }
                        is QuoteContract.QuoteState.Loading -> {
                            progressBar.visible()
                            textViewText.gone()
                            textViewAuthor.gone()
                        }
                        is QuoteContract.QuoteState.Success -> {
                            textViewAuthor.text = state.quote.quoteAuthor
                            textViewText.text = state.quote.quoteText
                            textViewText.visible()
                            textViewAuthor.visible()
                            progressBar.gone()
                        }
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                quoteViewModel.effect.collect {
                    when (it) {
                        is QuoteContract.Effect.ShowError -> {
                            Snackbar.make(root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewRefresh.setOnClickListenerWithDebounce(debounceTime = 2000L) {
                quoteViewModel.setEvent(QuoteContract.Event.FetchQuote)
            }
            imageViewHistory.setOnClickListener {
                findNavController().navigate(QuoteFragmentDirections.actionQuoteFragmentToFeatureHistory())
            }
        }
    }

}