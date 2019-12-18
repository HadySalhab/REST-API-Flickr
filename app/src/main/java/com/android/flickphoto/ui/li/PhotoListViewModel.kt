package com.android.flickphoto.ui.li

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.flickphoto.models.Photo

class PhotoListViewModel : ViewModel() {

    private var _photos = MutableLiveData<Photo>()
    val photos: LiveData<Photo>
        get() = _photos


}