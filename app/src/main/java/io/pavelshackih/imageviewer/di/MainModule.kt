package io.pavelshackih.imageviewer.di

import io.pavelshackih.imageviewer.domain.PhotoInteractor
import io.pavelshackih.imageviewer.presentation.common.list.ViewTypes
import io.pavelshackih.imageviewer.presentation.list.ListViewTypesProvider
import toothpick.config.Module

class MainModule : Module() {

    init {
        bind(DataModule::class.java)
        bind(PhotoInteractor::class.java).to(PhotoInteractor::class.java)
        bind(ViewTypes::class.java).toProviderInstance(ListViewTypesProvider())
    }
}