package dev.zurbaevi.favorite

import com.example.favorite.databinding.QuoteFavoriteItemBinding
import dev.zurbaevi.common.base.BaseViewHolder
import dev.zurbaevi.domain.model.Quote

class FavoriteViewHolder(
    private val binding: QuoteFavoriteItemBinding,
    private var clickListener: OnItemClickListener
) : BaseViewHolder<Quote, QuoteFavoriteItemBinding>(binding) {

    init {
        binding.root.setOnClickListener {
            clickListener.onItemClick()
        }
    }

    override fun bind() {
        getRowItem()?.let { quote ->
            binding.apply {
                textViewQuoteAuthor.text = quote.quoteAuthor
                textViewQuoteText.text = quote.quoteText
            }
        }
    }

}