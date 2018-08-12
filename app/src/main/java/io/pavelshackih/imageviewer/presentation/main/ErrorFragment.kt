package io.pavelshackih.imageviewer.presentation.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.pavelshackih.imageviewer.R

class ErrorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_error, container, false)
        root.findViewById<View>(R.id.button).setOnClickListener {
            val router = activity as Router
            router.navigateToList()
        }
        return root
    }
}