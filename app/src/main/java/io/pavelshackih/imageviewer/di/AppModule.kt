package io.pavelshackih.imageviewer.di

import android.app.Application
import io.pavelshackih.imageviewer.rx.AppScheduler
import io.pavelshackih.imageviewer.rx.RxSchedulers
import toothpick.smoothie.module.SmoothieApplicationModule

class AppModule(application: Application) : SmoothieApplicationModule(application) {

    init {
        bind(RxSchedulers::class.java).toInstance(AppScheduler())
    }
}