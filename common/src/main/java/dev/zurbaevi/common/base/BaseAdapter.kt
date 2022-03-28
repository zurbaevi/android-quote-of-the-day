package dev.zurbaevi.common.base

import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import dev.zurbaevi.common.util.ListAdapterSwipe

abstract class BaseAdapter<M : Any, WB : ViewBinding, VH : BaseViewHolder<M, WB>>(callback: DiffUtil.ItemCallback<M>) :
    ListAdapterSwipe<M, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings((getItem(position)))
        holder.bind()
    }

    override fun submitList(list: List<M>?) {
        super.submitList(list ?: emptyList())
    }

}