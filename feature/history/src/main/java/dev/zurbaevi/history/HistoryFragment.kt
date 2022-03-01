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
import dev.zurbaevi.common.exentsion.showShortToast
import dev.zurbaevi.common.exentsion.visible
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

    private fun initStateObservers() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                historyViewModel.uiState.collect {
                    when (val state = it.historyState) {
                        is HistoryContract.HistoryState.Idle -> {
                            progressBar.gone()
                            recyclerView.gone()
                        }
                        is HistoryContract.HistoryState.Loading -> {
                            recyclerView.gone()
                            progressBar.visible()
                        }
                        is HistoryContract.HistoryState.Success -> {
                            historyAdapter.submitList(state.quotes)
                            progressBar.gone()
                            recyclerView.visible()
                        }
                    }
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
                            showShortToast(it.message)
                            progressBar.gone()
                        }
                        is HistoryContract.Effect.Deleted -> {
                            showShortToast(getString(R.string.quotes_deleted))
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
                historyViewModel.setEvent(HistoryContract.Event.DeleteQuotes)
            }
        }
    }

    private fun firstInitState() {
        if (historyViewModel.currentState.historyState is HistoryContract.HistoryState.Idle) {
            historyViewModel.setEvent(HistoryContract.Event.GetQuotes)
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

}