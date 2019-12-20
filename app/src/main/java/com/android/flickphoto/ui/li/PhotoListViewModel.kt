package com.android.flickphoto.ui.li

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.flickphoto.models.Photo
import com.android.flickphoto.repositories.PhotoRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PhotoListViewModel(private val photoRepository: PhotoRepository) : ViewModel() {



    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _query = MutableLiveData<String>("")
    val query:LiveData<String>
    get() = _query




    fun getFlickrPhotos(query:String) {
        if(TextUtils.isEmpty(query)){

        viewModelScope.launch {
            _photos.value = photoRepository.fetchPhotos()

        }
        }else{
            viewModelScope.launch {
                _photos.value = photoRepository.searchPhotos(query)
            }
        }

    }
    fun changeQueryValue(query:String){
        _query.value = query
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}