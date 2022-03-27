package dev.zurbaevi.history

import dev.zurbaevi.common.base.BaseViewHolder
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.history.databinding.QuoteHistoryItemBinding

class HistoryViewHolder(private val binding: QuoteHistoryItemBinding) :
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