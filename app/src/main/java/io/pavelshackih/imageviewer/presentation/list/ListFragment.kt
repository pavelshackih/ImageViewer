package io.pavelshackih.imageviewer.presentation.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import io.pavelshackih.imageviewer.R
import io.pavelshackih.imageviewer.databinding.FragmentListBinding
import io.pavelshackih.imageviewer.presentation.main.Router
import io.pavelshackih.imageviewer.presentation.viewmodel.MainViewModel

class ListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.clickPhotoEvent.observe(this, Observer { photo ->
            photo?.let {
                val router = activity as Router
                router.navigateToPhoto(it)
            }
        })
        viewModel.errorEvent.observe(this, Observer {
            val router = activity as Router
            router.navigateToError()
        })
        return DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false).apply {
            setLifecycleOwner(this@ListFragment)
            model = viewModel
        }.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView? ?: throw IllegalStateException()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                searchView.post {
                    viewModel.onTextSubmit(text)
                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.onTextChange(text)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}