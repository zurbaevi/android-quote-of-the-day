package dev.zurbaevi.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.common.base.BaseFragment
import dev.zurbaevi.common.exentsion.inVisible
import dev.zurbaevi.common.exentsion.showShortSnackBar
import dev.zurbaevi.common.exentsion.visible
import dev.zurbaevi.history.databinding.FragmentHistoryBinding

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<HistoryContract.Event, HistoryContract.State, HistoryContract.Effect, FragmentHistoryBinding, HistoryViewModel>() {

    override val viewModel by viewModels<HistoryViewModel>()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        firstInitState()
        configurationRecyclerView()
        initListeners()
    }

    override fun renderState(state: HistoryContract.State) {
        when (state.historyState) {
            is HistoryContract.HistoryState.Idle -> hideAll()
            is HistoryContract.HistoryState.Empty -> hideAll()
            is HistoryContract.HistoryState.Loading -> showLoading()
            is HistoryContract.HistoryState.Success -> showData()
        }
    }

    override fun renderEffect(effect: HistoryContract.Effect) {
        when (effect) {
            is HistoryContract.Effect.ShowError -> {
                showShortSnackBar(effect.message)
                hideAll()
            }
            is HistoryContract.Effect.ShowInfoDeleteQuotes -> {
                showShortSnackBar(getString(R.string.quotes_deleted))
                hideAll()
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewDelete.setOnClickListener {
                viewModel.setEvent(HistoryContract.Event.OnDeleteQuotes)
            }
        }
    }

    private fun firstInitState() {
        if (viewModel.currentState.historyState is HistoryContract.HistoryState.Idle) {
            viewModel.setEvent(HistoryContract.Event.OnGetQuotes)
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
            recyclerView.adapter = historyAdapter
        }
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

    private fun showData() = with(binding) {
        imageViewEmpty.inVisible()
        progressBar.inVisible()
        historyAdapter.submitList(viewModel.currentState.quotes)
        recyclerView.visible()
    }

}