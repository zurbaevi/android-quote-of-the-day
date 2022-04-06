package dev.zurbaevi.favorite

import com.example.favorite.databinding.QuoteFavoriteItemBinding
import dev.zurbaevi.common.base.BaseViewHolder
import dev.zurbaevi.domain.model.Quote

class FavoriteViewHolder(
    private val binding: QuoteFavoriteItemBinding,
) : BaseViewHolder<Quote, QuoteFavoriteItemBinding>(binding) {

    override fun bind() {
        getRowItem()?.let { quote ->
            binding.apply {
                textViewQuoteAuthor.text = quote.quoteAuthor
                textViewQuoteText.text = quote.quoteText
            }
        }
    }

}