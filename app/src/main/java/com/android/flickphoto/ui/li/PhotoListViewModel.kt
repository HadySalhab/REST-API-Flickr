package com.android.flickphoto.ui.li

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.flickphoto.models.Photo
import com.android.flickphoto.repositories.PhotoRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PhotoListViewModel (private val photoRepository:PhotoRepository) : ViewModel() {

    private var _photos =MutableLiveData<List<Photo>>()
   val photos: LiveData<List<Photo>>
    get() = _photos

    init {
        getFlickrPhotos()
    }



    fun getFlickrPhotos() {
        viewModelScope.launch {
               _photos.value =  photoRepository.fetchPhotos()

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }



}