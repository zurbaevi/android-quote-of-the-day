package dev.zurbaevi.quoteoftheday.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import dev.zurbaevi.quoteoftheday.QuoteApplication
import dev.zurbaevi.quoteoftheday.dagger.factory.ViewModelFactory
import dev.zurbaevi.quoteoftheday.databinding.FragmentQuoteHistoryBinding
import dev.zurbaevi.quoteoftheday.ui.adapter.QuoteAdapter
import dev.zurbaevi.quoteoftheday.ui.viewmodel.QuoteHistoryViewModel
import dev.zurbaevi.quoteoftheday.util.Resource
import javax.inject.Inject

class QuoteHistoryFragment : Fragment() {

    private var _binding: FragmentQuoteHistoryBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var quoteHistoryViewModelFactory: ViewModelFactory
    private lateinit var quoteHistoryViewModel: QuoteHistoryViewModel

    private val quoteAdapter by lazy {
        QuoteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        QuoteApplication.appComponent.inject(this)
        _binding = FragmentQuoteHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quoteHistoryViewModel =
            ViewModelProvider(this,
                quoteHistoryViewModelFactory).get(QuoteHistoryViewModel::class.java)

        binding.apply {
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL))
            recyclerView.adapter = quoteAdapter

            quoteHistoryViewModel.quotes.observe(viewLifecycleOwner) {
                it?.let { resource ->
                    when (resource.status) {
                        Resource.Status.SUCCESS -> {
                            quoteAdapter.submitList(it.data)
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                        }
                        Resource.Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.GONE
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        Resource.Status.LOADING -> {
                            recyclerView.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}