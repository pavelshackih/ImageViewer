package io.pavelshackih.imageviewer.presentation.list

import io.pavelshackih.imageviewer.R
import io.pavelshackih.imageviewer.presentation.common.list.SimpleViewTypes
import io.pavelshackih.imageviewer.presentation.common.list.ViewTypes
import io.pavelshackih.imageviewer.presentation.viewmodel.PhotoViewModel
import javax.inject.Provider

class ListViewTypesProvider : Provider<ViewTypes> {

    override fun get(): ViewTypes {
        return SimpleViewTypes {
            bind(PhotoViewModel::class.java to R.layout.view_holder_photo)
        }
    }
}
