package io.pavelshackih.imageviewer.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppScheduler : RxSchedulers {

    override fun getMain(): Scheduler = AndroidSchedulers.mainThread()

    override fun getIo(): Scheduler = Schedulers.io()
}