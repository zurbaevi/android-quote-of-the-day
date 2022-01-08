package dev.zurbaevi.quoteoftheday.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.quoteoftheday.databinding.FragmentHistoryBinding

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyViewModel by viewModels<HistoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val historyAdapter = HistoryAdapter()

        binding.apply {
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.adapter = historyAdapter

            historyViewModel.historyUiState.observe(viewLifecycleOwner) {
                when {
                    it.isFetchingQuote -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    it.error.isNotEmpty() -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                    }
                    else -> {
                        historyAdapter.submitList(it.quotes)
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}