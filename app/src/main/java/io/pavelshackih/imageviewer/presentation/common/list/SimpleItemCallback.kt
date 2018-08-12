package io.pavelshackih.imageviewer.presentation.common.list

import android.support.v7.util.DiffUtil

class SimpleItemCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(first: Any, second: Any): Boolean = first === second

    override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
}
