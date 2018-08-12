package io.pavelshackih.imageviewer.presentation.bindings

import android.arch.lifecycle.LifecycleOwner
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import io.pavelshackih.imageviewer.presentation.common.list.DataBindingAdapter
import io.pavelshackih.imageviewer.presentation.common.list.ViewTypes

@BindingAdapter(value = ["items", "viewTypes"])
fun setItems(recycleView: RecyclerView,
             items: List<Any>?,
             viewTypes: ViewTypes) {
    if (recycleView.adapter == null) {
        recycleView.adapter = DataBindingAdapter(recycleView.context as LifecycleOwner, viewTypes)
        recycleView.setHasFixedSize(true)
    }

    items?.let {
        (recycleView.adapter as DataBindingAdapter).submitList(it)
    }
}

@BindingAdapter("glideCenterCrop")
fun glideCenterCrop(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
                .load(url)
                .apply(RequestOptions().apply { centerCrop() })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }
}

@BindingAdapter("glide")
fun glide(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
                .load(url)
                .into(imageView)
    }
}

@BindingAdapter("visible")
fun visible(view: View, visible: Boolean?) {
    visible?.let {
        if (it) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }
}