package dev.zurbaevi.common.util

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class ListAdapterSwipe<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    private val removedItems = arrayListOf<T>()

    @CallSuper
    override fun submitList(list: List<T>?) {
        submit(list, false)
    }

    private fun submit(list: List<T>?, isLocalSubmit: Boolean) {
        if (!isLocalSubmit) removedItems.clear()
        super.submitList(list)
    }

    fun removeItem(position: Int): T? {
        if (position >= itemCount) return null
        val item = currentList[position]
        removedItems.add(item)
        val actualList = currentList - removedItems
        if (actualList.isEmpty()) removedItems.clear()
        submit(actualList, true)
        return item
    }

    fun removeItem(item: T): T? {
        val position = currentList.indexOf(item)
        if (position == -1) return null
        return removeItem(position)
    }

    open fun isItemSwipe(viewHolder: RecyclerView.ViewHolder): Boolean {
        return true
    }

}