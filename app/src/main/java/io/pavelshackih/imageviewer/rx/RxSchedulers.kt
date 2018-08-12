package io.pavelshackih.imageviewer.rx

import io.reactivex.Scheduler

interface RxSchedulers {

    fun getMain(): Scheduler

    fun getIo(): Scheduler
}