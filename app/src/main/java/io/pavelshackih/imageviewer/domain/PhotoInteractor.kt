package io.pavelshackih.imageviewer.domain

import io.pavelshackih.imageviewer.model.Photo
import io.pavelshackih.imageviewer.data.network.FlickrService
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class PhotoInteractor @Inject constructor(private val service: FlickrService) {

    fun getRecent(): Single<List<Photo>> {
        return service.getRecent().map { it.page.photos }
    }

    fun getSearch(text: String): Single<List<Photo>> {
        return service.getSearch(text).map { it.page.photos }
    }
}
