package dev.zurbaevi.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import dev.zurbaevi.base.BaseAdapter
import dev.zurbaevi.base.BaseViewHolder
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.history.databinding.QuoteHistoryItemBinding

class HistoryAdapter :
    BaseAdapter<Quote, QuoteHistoryItemBinding, HistoryAdapter.ViewHolder>(QuoteDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object QuoteDiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem.quoteId == newItem.quoteId

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
    }

    class ViewHolder(private val binding: QuoteHistoryItemBinding) :
        BaseViewHolder<Quote, QuoteHistoryItemBinding>(binding) {

        override fun bind() {
            getRowItem()?.let { quote ->
                binding.apply {
                    textViewQuoteAuthor.text = quote.quoteAuthor
                    textViewQuoteText.text = quote.quoteText
                }
            }
        }
    }

}