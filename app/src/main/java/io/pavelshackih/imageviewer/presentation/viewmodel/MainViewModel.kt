package io.pavelshackih.imageviewer.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.pavelshackih.imageviewer.domain.PhotoInteractor
import io.pavelshackih.imageviewer.model.Photo
import io.pavelshackih.imageviewer.presentation.common.list.ViewTypes
import io.pavelshackih.imageviewer.presentation.common.viewmodel.AppViewModel
import io.pavelshackih.imageviewer.presentation.common.viewmodel.SingleLiveEvent
import io.pavelshackih.imageviewer.rx.RxSchedulers
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import toothpick.Toothpick
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel : AppViewModel() {

    @Inject
    lateinit var interactor: PhotoInteractor

    @Inject
    lateinit var viewProviders: ViewTypes

    @Inject
    lateinit var rxSchedulers: RxSchedulers

    val viewTypes = MutableLiveData<ViewTypes>()
    val items = MutableLiveData<List<PhotoViewModel>>()
    val clickPhotoEvent = SingleLiveEvent<Photo>()
    val errorEvent = SingleLiveEvent<Throwable>()
    val progress = MutableLiveData<Boolean>().apply {
        value = true
    }

    private var searchText: String = ""
    private val searchSubject: BehaviorSubject<String>

    init {
        Toothpick.inject(this, scope)
        viewTypes.value = viewProviders
        applyList(null)
        searchSubject = BehaviorSubject.create<String>()
        compositeDisposable.add(
                searchSubject
                        .debounce(INPUT_DELAY, TimeUnit.MILLISECONDS)
                        .observeOn(rxSchedulers.getMain())
                        .subscribe { onSearch(it) }
        )
    }

    fun onTextSubmit(text: String?) {
        text?.let {
            searchSubject.onNext(it)
        }
    }

    fun onTextChange(text: String?) {
        text?.let {
            searchSubject.onNext(it)
        }
    }

    private fun onSearch(text: String?) {
        if (searchText != text) {
            applyList(text)
        }
    }

    private fun applyList(text: String?) {
        val single: Single<List<Photo>> =
                if (text != null && text.isNotBlank()) {
                    searchText = text
                    interactor.getSearch(text)
                } else {
                    searchText = ""
                    interactor.getRecent()
                }
        progress.value = true
        compositeDisposable.add(
                single.subscribeOn(rxSchedulers.getIo())
                        .observeOn(rxSchedulers.getMain())
                        .subscribe(
                                { list ->
                                    progress.value = false
                                    items.value = list.map { PhotoViewModel(it, clickPhotoEvent) }
                                },
                                { throwable ->
                                    Log.d(TAG, "Error while loading photos", throwable)
                                    errorEvent.value = throwable
                                })
        )
    }

    fun clickTryAgain() {
        applyList(searchText)
    }

    companion object {
        const val TAG = "MainViewModel"
        const val INPUT_DELAY = 400L
    }
}