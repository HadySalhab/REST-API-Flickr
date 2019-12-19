package com.android.flickphoto.ui.li

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.flickphoto.models.Photo
import com.android.flickphoto.repositories.PhotoRepository

class PhotoListViewModel (private val photoRepository:PhotoRepository) : ViewModel() {

    private var _photos = MutableLiveData<Photo>()
    val photos: LiveData<Photo>
        get() = _photos


}