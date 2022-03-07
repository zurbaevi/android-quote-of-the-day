package dev.zurbaevi.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.common.base.BaseFragment
import dev.zurbaevi.common.exentsion.gone
import dev.zurbaevi.common.exentsion.showShortSnackBar
import dev.zurbaevi.common.exentsion.visible
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.history.databinding.FragmentHistoryBinding

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val historyViewModel by viewModels<HistoryViewModel>()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate

    private val historyAdapter by lazy {
        HistoryAdapter()
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        firstInitState()
        configurationRecyclerView()
        initStateObservers()
        initEffectObservers()
        initListeners()
    }

    private fun initStateObservers(): Unit = with(binding) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            historyViewModel.uiState.collect {
                when (val state = it.historyState) {
                    is HistoryContract.HistoryState.Idle -> hideAll()
                    is HistoryContract.HistoryState.Loading -> showLoading()
                    is HistoryContract.HistoryState.Success -> showData(state.quotes)
                }
            }
        }
    }

    private fun initEffectObservers() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                historyViewModel.effect.collect {
                    when (it) {
                        is HistoryContract.Effect.Error -> {
                            showShortSnackBar(it.message)
                            progressBar.gone()
                        }
                        is HistoryContract.Effect.Deleted -> {
                            showShortSnackBar(getString(R.string.quotes_deleted))
                            progressBar.gone()
                        }
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            imageViewDelete.setOnClickListener {
                historyViewModel.setEvent(HistoryContract.Event.OnDeleteQuotes)
            }
        }
    }

    private fun firstInitState() {
        if (historyViewModel.currentState.historyState is HistoryContract.HistoryState.Idle) {
            historyViewModel.setEvent(HistoryContract.Event.OnGetQuotes)
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
        progressBar.gone()
        recyclerView.gone()
    }

    private fun showLoading() = with(binding) {
        recyclerView.gone()
        progressBar.visible()
    }

    private fun showData(quotes: List<Quote>) = with(binding) {
        progressBar.gone()
        historyAdapter.submitList(quotes)
        progressBar.gone()
        recyclerView.visible()
    }

}