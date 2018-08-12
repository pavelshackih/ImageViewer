package io.pavelshackih.imageviewer.presentation.common.list

import android.arch.lifecycle.LifecycleOwner
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DataBindingAdapter(private val lifecycleOwner: LifecycleOwner,
                         private val viewTypesProvider: ViewTypes) :
        ListAdapter<Any, DataBindingViewHolder>(SimpleItemCallback()) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): DataBindingViewHolder {
        if (!this::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return DataBindingViewHolder(inflateViewHolder(parent, type))
    }

    private fun inflateViewHolder(parent: ViewGroup, type: Int): View {
        return DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                viewTypesProvider.getLayoutId(type),
                parent,
                false).apply {
            setLifecycleOwner(lifecycleOwner)
        }.root
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypesProvider.getViewTypeBy(getItem(position)::class.java)
    }

    override fun onBindViewHolder(viewHolder: DataBindingViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }
}