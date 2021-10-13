package dev.zurbaevi.quoteoftheday.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.zurbaevi.quoteoftheday.data.model.Quote
import dev.zurbaevi.quoteoftheday.databinding.QuoteHistoryItemBinding

class QuoteAdapter : ListAdapter<Quote, QuoteAdapter.ViewHolder>(TaskDiffCallback) {

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

    object TaskDiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem.quoteId == newItem.quoteId

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
    }

    inner class ViewHolder(private val binding: QuoteHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(quote: Quote) = with(binding) {
            textViewQuoteAuthor.text = quote.quoteAuthor
            textViewQuoteText.text = quote.quoteText
        }
    }

}