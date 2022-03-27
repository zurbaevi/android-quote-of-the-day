package dev.zurbaevi.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import dev.zurbaevi.common.base.BaseAdapter
import dev.zurbaevi.domain.model.Quote
import dev.zurbaevi.history.databinding.QuoteHistoryItemBinding

class HistoryAdapter :
    BaseAdapter<Quote, QuoteHistoryItemBinding, HistoryViewHolder>(HistoryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            QuoteHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object HistoryDiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem.quoteId == newItem.quoteId

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote) = oldItem == newItem
    }

}