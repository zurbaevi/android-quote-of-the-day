package dev.zurbaevi.common.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ItemSwipeHandler<M : Any, WB : ViewBinding>(
    private val adapter: ListAdapterSwipe<M, WB, *>?,
    private val onItemRemoved: ((item: M, items: List<M>) -> Unit)? = null,
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.bindingAdapterPosition
        val item = adapter?.removeItem(position) ?: return
        onItemRemoved?.invoke(item, adapter.getActualListSize())
    }

}