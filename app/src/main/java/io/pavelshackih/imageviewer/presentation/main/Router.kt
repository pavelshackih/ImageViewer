package io.pavelshackih.imageviewer.presentation.main

import io.pavelshackih.imageviewer.model.Photo

interface Router {

    fun navigateToPhoto(photo: Photo)

    fun navigateToList()

    fun navigateToError()
}