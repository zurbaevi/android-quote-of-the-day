package dev.zurbaevi.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.presentation.databinding.QuoteHistoryItemBinding

class HistoryAdapter : ListAdapter<Quote, HistoryAdapter.ViewHolder>(QuoteDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(getItem(position))
    }

    object QuoteDiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem.quoteId == newItem.quoteId

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
    }

    class ViewHolder(private val binding: QuoteHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(quote: Quote) = with(binding) {
            textViewQuoteAuthor.text = quote.quoteAuthor
            textViewQuoteText.text = quote.quoteText
        }
    }

}