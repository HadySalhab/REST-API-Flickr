package com.android.flickphoto.ui.li


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.flickphoto.R
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

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_photo_list,menu)
    }


}
