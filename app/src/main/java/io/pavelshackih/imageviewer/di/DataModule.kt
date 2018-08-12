package io.pavelshackih.imageviewer.di

import io.pavelshackih.imageviewer.data.network.FlickrService
import toothpick.config.Module

class DataModule : Module() {

    init {
        bind(FlickrService::class.java).toInstance(FlickrService.api)
    }
}
