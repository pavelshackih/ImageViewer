package io.pavelshackih.imageviewer.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.pavelshackih.imageviewer.R
import io.pavelshackih.imageviewer.model.Photo
import io.pavelshackih.imageviewer.presentation.list.ListFragment
import io.pavelshackih.imageviewer.presentation.photo.PhotoFragment

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToList()
        }
    }

    override fun navigateToPhoto(photo: Photo) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, PhotoFragment.newInstance(photo))
                .addToBackStack(null)
                .commit()
    }

    override fun navigateToList() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, ListFragment())
                .commit()
    }

    override fun navigateToError() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, ErrorFragment())
                .commit()
    }
}
