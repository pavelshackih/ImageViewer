package io.pavelshackih.imageviewer.presentation.photo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.pavelshackih.imageviewer.R
import io.pavelshackih.imageviewer.model.Photo
import io.pavelshackih.imageviewer.databinding.FragmentPhotoBinding
import io.pavelshackih.imageviewer.presentation.ext.getViewModel
import io.pavelshackih.imageviewer.presentation.viewmodel.MainViewModel
import io.pavelshackih.imageviewer.presentation.viewmodel.PhotoViewModel

class PhotoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val photo = arguments?.getParcelable(PHOTO) as Photo

        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PhotoViewModel(photo, getViewModel(MainViewModel::class.java).clickPhotoEvent) as T
            }
        }
        val viewModel = getViewModel(PhotoViewModel::class.java, factory)

        return DataBindingUtil.inflate<FragmentPhotoBinding>(inflater, R.layout.fragment_photo, container, false).apply {
            model = viewModel
        }.root
    }

    companion object {

        const val PHOTO = "glideCenterCrop"

        @JvmStatic
        fun newInstance(photo: Photo): PhotoFragment {
            return PhotoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PHOTO, photo)
                }
            }
        }
    }
}