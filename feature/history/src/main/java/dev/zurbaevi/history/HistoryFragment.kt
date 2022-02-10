package dev.zurbaevi.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.base.BaseFragment
import dev.zurbaevi.common.util.exentsion.gone
import dev.zurbaevi.common.util.exentsion.visible
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
        configurationRecyclerView()
        initObservers()
        if (historyViewModel.currentState.historyState is HistoryContract.HistoryState.Idle) {
            historyViewModel.setEvent(HistoryContract.Event.OnGetQuotes)
        }
    }

    private fun initObservers() {
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
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                historyViewModel.effect.collect {
                    when (it) {
                        is HistoryContract.Effect.ShowError -> {
                            Snackbar.make(root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
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