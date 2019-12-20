package com.android.flickphoto.ui.li


import android.os.Bundle
import android.view.*
import android.view.MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.flickphoto.R
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import com.android.flickphoto.databinding.FragmentPhotoListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */

class PhotoListFragment : Fragment() {

    private val photoListViewModel: PhotoListViewModel by viewModel()
    private lateinit var binding: FragmentPhotoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_photo_list, container, false)
        binding.viewModel = photoListViewModel
        binding.setLifecycleOwner(this.viewLifecycleOwner)
        binding.photosRecyclerview.adapter = PhotoListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoListViewModel.query.observe(viewLifecycleOwner, Observer{query->
            photoListViewModel.getFlickrPhotos(query)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_photo_list, menu)
        setUpSearchViewListener(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_item_clear->{
                photoListViewModel.changeQueryValue("")
                true
            }
            else->super.onOptionsItemSelected(item)

        }
            }

    private fun setUpSearchViewListener(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
        val searchView = searchItem.actionView as SearchView
        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    photoListViewModel.changeQueryValue(query?:"")
                    onActionViewCollapsed()
                    searchItem.collapseActionView()
                    hideSoftKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
            setOnSearchClickListener {
                searchView.setQuery(photoListViewModel.query.value?:"",false)
            }


        }

    }
    fun hideSoftKeyboard(){
        view?.let {
            val imm = context?.getSystemService<InputMethodManager>()

            imm?.hideSoftInputFromWindow(
                it.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }


}
