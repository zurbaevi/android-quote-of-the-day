package dev.zurbaevi.common.util

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import dev.zurbaevi.common.base.BaseAdapter
import dev.zurbaevi.common.base.BaseViewHolder

abstract class ListAdapterSwipe<M : Any, WB : ViewBinding, VH : BaseViewHolder<M, WB>>(
    diffCallback: DiffUtil.ItemCallback<M>
) : BaseAdapter<M, WB, VH>(diffCallback) {

    private val removedItems = arrayListOf<M>()
    private var actualList = listOf<M>()

    @CallSuper
    override fun submitList(list: List<M>?) {
        submit(list, false)
    }

    private fun submit(list: List<M>?, isLocalSubmit: Boolean) {
        if (!isLocalSubmit) removedItems.clear()
        super.submitList(list)
    }

    fun removeItem(position: Int): M? {
        if (position >= itemCount) return null
        val item = currentList[position]
        removedItems.add(item)
        actualList = (currentList - removedItems).toList()
        if (actualList.isEmpty()) removedItems.clear()
        submit(actualList, true)
        return item
    }

    fun getActualListSize(): List<M> {
        return actualList
    }

}