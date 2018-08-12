package io.pavelshackih.imageviewer.presentation.common.viewmodel

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import io.pavelshackih.imageviewer.di.APP_SCOPE
import io.pavelshackih.imageviewer.di.DataModule
import io.pavelshackih.imageviewer.di.MainModule
import io.pavelshackih.imageviewer.di.VIEW_MODEL_SCOPE
import io.reactivex.disposables.CompositeDisposable
import toothpick.Scope
import toothpick.Toothpick

/**
 * Base class for all view models in application.
 * Provide DI support for [VIEW_MODEL_SCOPE] scope.
 */
open class AppViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    protected val scope: Scope = Toothpick.openScopes(APP_SCOPE, VIEW_MODEL_SCOPE)

    init {
        scope.installModules(MainModule(), DataModule())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope(VIEW_MODEL_SCOPE)
        compositeDisposable.dispose()
    }
}
