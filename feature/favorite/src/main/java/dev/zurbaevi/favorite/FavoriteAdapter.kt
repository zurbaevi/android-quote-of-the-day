package dev.zurbaevi.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.favorite.databinding.QuoteFavoriteItemBinding
import dev.zurbaevi.common.base.BaseAdapter
import dev.zurbaevi.domain.model.Quote

class FavoriteAdapter(private var clickListener: OnItemClickListener) :
    BaseAdapter<Quote, QuoteFavoriteItemBinding, FavoriteViewHolder>(HistoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            QuoteFavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    object HistoryDiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem.quoteId == newItem.quoteId

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
    }

}