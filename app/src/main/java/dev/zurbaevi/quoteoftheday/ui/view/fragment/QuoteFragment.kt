package dev.zurbaevi.quoteoftheday.ui.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.zurbaevi.quoteoftheday.QuoteApplication
import dev.zurbaevi.quoteoftheday.dagger.factory.ViewModelFactory
import dev.zurbaevi.quoteoftheday.databinding.FragmentQuoteBinding
import dev.zurbaevi.quoteoftheday.ui.viewmodel.QuoteViewModel
import dev.zurbaevi.quoteoftheday.util.Resource
import javax.inject.Inject

class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var quoteViewModelFactory: ViewModelFactory
    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        QuoteApplication.appComponent.inject(this)
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quoteViewModel =
            ViewModelProvider(this, quoteViewModelFactory).get(QuoteViewModel::class.java)

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
                            Log.d("TAG", "onViewCreated: ${resource.message}")
                        }
                        Resource.Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            textViewText.visibility = View.GONE
                            textViewAuthor.visibility = View.GONE
                        }
                    }
                }
            }
            imageViewRefresh.setOnLongClickListener {
                quoteViewModel.getQuote()
                true
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