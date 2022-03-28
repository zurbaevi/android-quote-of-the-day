package dev.zurbaevi.common.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemSwipeHandler<T>(
    private val adapter: ListAdapterSwipe<T, *>?,
    private val onItemRemoved: ((item: T) -> Unit)? = null
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.bindingAdapterPosition
        val item = adapter?.removeItem(position) ?: return
        onItemRemoved?.invoke(item)
    }

}