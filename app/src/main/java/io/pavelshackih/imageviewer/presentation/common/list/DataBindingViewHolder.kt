package io.pavelshackih.imageviewer.presentation.common.list

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import io.pavelshackih.imageviewer.BR

class DataBindingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ViewDataBinding = DataBindingUtil.getBinding(itemView) ?: throw IllegalStateException()

    fun bind(model: Any) {
        binding.setVariable(BR.model, model)
        binding.executePendingBindings()
    }
}
