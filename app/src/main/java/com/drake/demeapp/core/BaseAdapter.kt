package com.drake.demeapp.core

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T> : ListAdapter<T, BaseViewHolder<ViewBinding>>(BaseDiffCallback<T>()) {

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        bind(holder.binding, position)
        holder.itemView.setOnClickListener {
            setOnClickItem(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(parent, viewType)

    open fun getViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding

    protected abstract fun bind(binding: ViewBinding, position: Int)

    protected open fun setOnClickItem(position: Int) {}
}