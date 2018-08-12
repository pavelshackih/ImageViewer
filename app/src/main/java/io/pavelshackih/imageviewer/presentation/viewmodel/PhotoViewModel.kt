package io.pavelshackih.imageviewer.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.pavelshackih.imageviewer.model.Photo
import io.pavelshackih.imageviewer.presentation.common.viewmodel.AppViewModel
import io.pavelshackih.imageviewer.presentation.common.viewmodel.SingleLiveEvent

class PhotoViewModel(private val model: Photo, private val parent: SingleLiveEvent<Photo>) : AppViewModel() {

    val photo: LiveData<Photo>

    init {
        photo = MutableLiveData<Photo>()
        photo.value = model
    }

    fun onPhotoClick() {
        parent.value = model
    }
}