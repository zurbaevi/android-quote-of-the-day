package dev.zurbaevi.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.common.base.BaseFragment
import dev.zurbaevi.common.exentsion.*
import dev.zurbaevi.common.util.NavControllerStateHandle
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.home.databinding.FragmentHomeBinding
import java.util.*

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeContract.Event, HomeContract.State, HomeContract.Effect, FragmentHomeBinding, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        initFirstState()
        initListeners()
        getInfoAboutSwipedDeleteQuote()
    }

    override fun renderState(state: HomeContract.State) {
        when (state.homeState) {
            is HomeContract.HomeState.Idle -> hideAll()
            is HomeContract.HomeState.Loading -> showLoading()
            is HomeContract.HomeState.Error -> showError()
            is HomeContract.HomeState.Success -> showData(state.quote, state.quoteIsFavorite)
        }
    }

    override fun renderEffect(effect: HomeContract.Effect) {
        when (effect) {
            is HomeContract.Effect.ShowSnackBar -> showLongSnackBar(effect.message)
            is HomeContract.Effect.ShowSnackBarChangeLanguage -> showLongSnackBar(
                "${getString(R.string.successfully_changed_the_language)} ${
                    effect.language.uppercase(
                        Locale.getDefault()
                    )
                }"
            )
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.homeState is HomeContract.HomeState.Idle) {
            viewModel.setEvent(HomeContract.Event.OnFetchQuote)
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewRefresh.apply {
                setOnClickListenerWithDebounce(debounceTime = 2000L) {
                    viewModel.setEvent(HomeContract.Event.OnFetchQuote)
                    startAnimation(
                        AnimationUtils.loadAnimation(
                            requireContext(),
                            R.anim.rotate_anim
                        )
                    )
                }
            }
            imageViewHistory.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFeatureHistory())
            }
            imageViewFavorite.apply {
                binding.apply {
                    setOnClickListener {
                        when (viewModel.currentState.quoteIsFavorite) {
                            true -> viewModel.setEvent(HomeContract.Event.OnDeleteFavoriteQuote)
                            false -> viewModel.setEvent(HomeContract.Event.OnInsertFavoriteQuote)
                        }
                    }
                    setOnLongClickListener {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFeatureFavorite())
                        true
                    }
                }
            }
            imageViewLanguage.setOnClickListener {
                viewModel.setEvent(HomeContract.Event.OnChangeLanguageQuote)
            }
        }
    }

    private fun getInfoAboutSwipedDeleteQuote() {
        if (viewModel.currentState.homeState is HomeContract.HomeState.Success) {
            NavControllerStateHandle<Boolean>(findNavController())
                .getCurrentBackStackEntry("swiped")?.let { swiped ->
                    if (swiped) {
                        viewModel.setEvent(HomeContract.Event.OnCheckFavoriteQuote)
                    }
                }
        }
    }

    private fun hideAll() = with(binding) {
        progressBar.inVisible()
        imageViewError.inVisible()
        textViewQuoteText.inVisible()
        textViewQuoteAuthor.inVisible()
    }

    private fun showLoading() = with(binding) {
        progressBar.visible()
        imageViewError.inVisible()
        textViewQuoteText.inVisible()
        textViewQuoteAuthor.inVisible()
    }

    private fun showError() = with(binding) {
        progressBar.inVisible()
        imageViewError.visible()
        textViewQuoteText.inVisible()
        textViewQuoteAuthor.inVisible()
    }

    private fun showData(quote: Quote, quoteIsFavorite: Boolean) = with(binding) {
        textViewQuoteAuthor.text = quote.quoteAuthor
        textViewQuoteText.text = quote.quoteText
        imageViewFavorite.setImageIfResource(
            quoteIsFavorite,
            R.drawable.ic_favorite,
            R.drawable.ic_favorite_border
        )
        textViewQuoteText.visible()
        textViewQuoteAuthor.visible()
        imageViewError.inVisible()
        progressBar.inVisible()
    }

}