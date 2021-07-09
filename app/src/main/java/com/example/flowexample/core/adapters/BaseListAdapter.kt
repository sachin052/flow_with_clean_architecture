package com.example.flowexample.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


// Base Paged List Adapter for pagination
abstract class BaseListAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

    abstract fun onItemClickListener():OnItemClickListener<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding, onItemClickListener())
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) =
        holder.bind(getItem(position))



    override fun getItemViewType(position: Int): Int =inflateLayout()

    abstract fun inflateLayout():Int

    fun getIndexItem(index:Int):T{
        return getItem(index)
    }

}

class DataBindingViewHolder<T>(
    private val binding: ViewDataBinding,
    private val onItemClickListener: OnItemClickListener<T>
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.model, item)
        binding.setVariable(BR._all, item)
        binding.root.setOnClickListener { onItemClickListener.onItemClick(item) }
        binding.executePendingBindings()
    }
}

interface OnItemClickListener<T> {
    fun onItemClick(item: T)
}