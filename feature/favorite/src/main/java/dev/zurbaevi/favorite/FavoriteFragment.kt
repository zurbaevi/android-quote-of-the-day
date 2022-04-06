package dev.zurbaevi.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.favorite.R
import com.example.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.common.base.BaseFragment
import dev.zurbaevi.common.exentsion.inVisible
import dev.zurbaevi.common.exentsion.showLongSnackBar
import dev.zurbaevi.common.exentsion.showShortSnackBar
import dev.zurbaevi.common.exentsion.visible
import dev.zurbaevi.common.util.ItemSwipeHandler
import dev.zurbaevi.common.util.NavControllerStateHandle
import dev.zurbaevi.domain.model.Quote

@AndroidEntryPoint
class FavoriteFragment :
    BaseFragment<FavoriteContract.Event, FavoriteContract.State, FavoriteContract.Effect, FragmentFavoriteBinding, FavoriteViewModel>() {

    override val viewModel: FavoriteViewModel by viewModels()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    private var favoriteAdapter: FavoriteAdapter? = null

    override fun prepareView(savedInstanceState: Bundle?) {
        initAdapter()
        initFirstState()
        initListeners()
        configurationRecyclerView()
        implementItemTouchHelper()
    }

    override fun renderState(state: FavoriteContract.State) {
        when (state.favoriteState) {
            is FavoriteContract.FavoriteState.Empty -> hideAll()
            is FavoriteContract.FavoriteState.Idle -> hideAll()
            is FavoriteContract.FavoriteState.Loading -> showLoading()
            is FavoriteContract.FavoriteState.Success -> showData(state.quotes)
        }
    }

    override fun renderEffect(effect: FavoriteContract.Effect) {
        when (effect) {
            is FavoriteContract.Effect.ShowSnackBarError -> showShortSnackBar(effect.message)
            is FavoriteContract.Effect.ShowSnackBarDeleteQuote -> showShortSnackBar(getString(R.string.quote_deleted))
            is FavoriteContract.Effect.ShowSnackBarDeleteQuotes -> {
                showShortSnackBar(getString(R.string.quotes_deleted))
                setInfoAboutSwipedDeleteQuotes()
            }
            FavoriteContract.Effect.ShowSnackBarQuotesEmpty -> showShortSnackBar(getString(R.string.quotes_already_empty))
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.favoriteState is FavoriteContract.FavoriteState.Idle) {
            viewModel.setEvent(FavoriteContract.Event.OnGetQuotes)
        }
    }

    private fun initAdapter() {
        favoriteAdapter = FavoriteAdapter {
            showLongSnackBar(getString(R.string.swipe_left_or_right_to_delete_quote))
        }
    }

    private fun configurationRecyclerView() {
        binding.apply {
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.adapter = favoriteAdapter
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            imageViewDelete.setOnClickListener {
                viewModel.setEvent(FavoriteContract.Event.OnDeleteQuotes)
            }
        }
    }

    private fun implementItemTouchHelper() {
        ItemTouchHelper(ItemSwipeHandler(favoriteAdapter) { quote, quotes ->
            viewModel.setEvent(FavoriteContract.Event.OnDeleteQuote(quote))
            viewModel.setEvent(FavoriteContract.Event.OnUpdateQuote(quotes))
            setInfoAboutSwipedDeleteQuote()
        }).attachToRecyclerView(binding.recyclerView)
    }

    private fun setInfoAboutSwipedDeleteQuote() {
        NavControllerStateHandle<Boolean>(findNavController()).savedPreviousBackStackEntry(
            "swiped",
            true
        )
    }

    private fun setInfoAboutSwipedDeleteQuotes() {
        NavControllerStateHandle<Boolean>(findNavController()).savedPreviousBackStackEntry(
            "deleted",
            true
        )
    }

    private fun hideAll() = with(binding) {
        imageViewEmpty.visible()
        progressBar.inVisible()
        recyclerView.inVisible()
    }

    private fun showLoading() = with(binding) {
        recyclerView.inVisible()
        imageViewEmpty.inVisible()
        progressBar.visible()
    }

    private fun showData(quotes: List<Quote>) = with(binding) {
        imageViewEmpty.inVisible()
        progressBar.inVisible()
        favoriteAdapter?.submitList(quotes)
        recyclerView.visible()
    }

    override fun onDestroyView() {
        favoriteAdapter = null
        super.onDestroyView()
    }

}