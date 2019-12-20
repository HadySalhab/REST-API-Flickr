package com.android.flickphoto.ui.li

import android.app.Application
import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.*
import com.android.flickphoto.models.Photo
import com.android.flickphoto.repositories.PhotoRepository
import com.android.flickphoto.util.PreferencesStorage
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PhotoListViewModel(private val photoRepository: PhotoRepository,private val app: Application) : AndroidViewModel(app) {



    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _query = MutableLiveData<String>(PreferencesStorage.getStoredQuery(app))
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
        PreferencesStorage.setStoredQuery(app,query)
        _query.value = query
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}