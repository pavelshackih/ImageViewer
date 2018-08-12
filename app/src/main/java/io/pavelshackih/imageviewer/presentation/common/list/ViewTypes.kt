package io.pavelshackih.imageviewer.presentation.common.list

import android.support.annotation.LayoutRes

interface ViewTypes {

    @LayoutRes
    fun getLayoutId(viewType: Int): Int

    fun getViewTypeBy(clazz: Class<*>): Int
}