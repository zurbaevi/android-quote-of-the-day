package dev.zurbaevi.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        configurationRecyclerView()
        implementItemTouchHelper()
    }

    override fun renderState(state: FavoriteContract.State) {
        when (state.favoriteState) {
            FavoriteContract.FavoriteState.Empty -> hideAll()
            FavoriteContract.FavoriteState.Idle -> hideAll()
            FavoriteContract.FavoriteState.Loading -> showLoading()
            FavoriteContract.FavoriteState.Success -> showData(state.quotes)
        }
    }

    override fun renderEffect(effect: FavoriteContract.Effect) {
        when (effect) {
            is FavoriteContract.Effect.ShowSnackBar -> showShortSnackBar(effect.message)
            is FavoriteContract.Effect.ShowSnackBarDeleteQuote -> showShortSnackBar(getString(R.string.quote_deleted))
        }
    }

    private fun initFirstState() {
        if (viewModel.currentState.favoriteState is FavoriteContract.FavoriteState.Idle) {
            viewModel.setEvent(FavoriteContract.Event.OnGetQuotes)
        }
    }

    private fun initAdapter() {
        favoriteAdapter = FavoriteAdapter(object : OnItemClickListener {
            override fun onItemClick() {
                showLongSnackBar(getString(R.string.swipe_left_or_right_to_delete_quote))
            }
        })
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

    private fun implementItemTouchHelper() {
        ItemTouchHelper(ItemSwipeHandler(favoriteAdapter) { quote ->
            viewModel.setEvent(FavoriteContract.Event.OnDeleteQuote(quote))
        }).attachToRecyclerView(binding.recyclerView)
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