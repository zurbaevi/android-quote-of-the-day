package dev.zurbaevi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.common.base.BaseFragment
import dev.zurbaevi.common.exentsion.gone
import dev.zurbaevi.common.exentsion.setOnClickListenerWithDebounce
import dev.zurbaevi.common.exentsion.showLongSnackBar
import dev.zurbaevi.common.exentsion.visible
import dev.zurbaevi.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val quoteViewModel by viewModels<HomeViewModel>()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        initListeners()
        initStateObservers()
        initEffectObservers()
    }

    private fun initStateObservers(): Unit = with(binding) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            quoteViewModel.uiState.collect {
                when (val state = it.homeState) {
                    is HomeContract.HomeState.Idle -> {
                        progressBar.gone()
                        textViewText.gone()
                        textViewAuthor.gone()
                    }
                    is HomeContract.HomeState.Loading -> {
                        progressBar.visible()
                        textViewText.gone()
                        textViewAuthor.gone()
                    }
                    is HomeContract.HomeState.Success -> {
                        textViewAuthor.text = state.quote.quoteAuthor
                        textViewText.text = state.quote.quoteText
                        textViewText.visible()
                        textViewAuthor.visible()
                        progressBar.gone()
                    }
                }
            }
        }
    }

    private fun initEffectObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            quoteViewModel.effect.collect {
                when (it) {
                    is HomeContract.Effect.Error -> {
                        showLongSnackBar(it.message)
                    }
                }
            }
        }
    }

    private fun initFirstState() {
        if (quoteViewModel.currentState.homeState is HomeContract.HomeState.Idle) {
            quoteViewModel.setEvent(HomeContract.Event.FetchQuote)
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewRefresh.setOnClickListenerWithDebounce(debounceTime = 2000L) {
                quoteViewModel.setEvent(HomeContract.Event.FetchQuote)
            }
            imageViewHistory.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFeatureHistory())
            }
        }
    }

}