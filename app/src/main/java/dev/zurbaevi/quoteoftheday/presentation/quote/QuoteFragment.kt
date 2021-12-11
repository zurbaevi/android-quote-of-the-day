package dev.zurbaevi.quoteoftheday.presentation.quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.zurbaevi.quoteoftheday.databinding.FragmentQuoteBinding
import dev.zurbaevi.quoteoftheday.util.Resource

@AndroidEntryPoint
class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            quoteViewModel.quote.observe(viewLifecycleOwner) {
                it?.let { resource ->
                    when (resource.status) {
                        Resource.Status.SUCCESS -> {
                            textViewAuthor.text = it.data?.quoteAuthor
                            textViewText.text = it.data?.quoteText
                            textViewText.visibility = View.VISIBLE
                            textViewAuthor.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        Resource.Status.ERROR -> {
                            textViewText.visibility = View.GONE
                            textViewAuthor.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        }
                        Resource.Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            textViewText.visibility = View.GONE
                            textViewAuthor.visibility = View.GONE
                        }
                    }
                }
            }
            imageViewRefresh.setOnClickListener {
                quoteViewModel.getQuote()
            }
            imageViewHistory.setOnClickListener {
                findNavController().navigate(QuoteFragmentDirections.actionQuoteFragmentToQuoteHistoryFragment())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}