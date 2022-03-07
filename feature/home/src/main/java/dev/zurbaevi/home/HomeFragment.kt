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
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()

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
            homeViewModel.uiState.collect {
                when (val state = it.homeState) {
                    is HomeContract.HomeState.Idle -> hideAll()
                    is HomeContract.HomeState.Loading -> showLoading()
                    is HomeContract.HomeState.Success -> showData(state.quote)
                }
            }
        }
    }

    private fun initEffectObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            homeViewModel.effect.collect {
                when (it) {
                    is HomeContract.Effect.Error -> {
                        showLongSnackBar(it.message)
                    }
                }
            }
        }
    }

    private fun initFirstState() {
        if (homeViewModel.currentState.homeState is HomeContract.HomeState.Idle) {
            homeViewModel.setEvent(HomeContract.Event.OnFetchQuote)
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewRefresh.setOnClickListenerWithDebounce(debounceTime = 2000L) {
                homeViewModel.setEvent(HomeContract.Event.OnFetchQuote)
            }
            imageViewHistory.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFeatureHistory())
            }
        }
    }

    private fun hideAll() = with(binding) {
        progressBar.gone()
        textViewText.gone()
        textViewAuthor.gone()
    }

    private fun showLoading() = with(binding) {
        progressBar.visible()
        textViewText.gone()
        textViewAuthor.gone()
    }

    private fun showData(quote: Quote) = with(binding) {
        textViewAuthor.text = quote.quoteAuthor
        textViewText.text = quote.quoteText
        textViewText.visible()
        textViewAuthor.visible()
        progressBar.gone()
    }

}