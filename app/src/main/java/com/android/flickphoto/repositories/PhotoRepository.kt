package com.android.flickphoto.repositories

import androidx.lifecycle.LiveData
import com.android.flickphoto.models.Photo
import com.android.flickphoto.requests.FlickrApiClient
import com.android.flickphoto.requests.responses.FlickrResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class PhotoRepository (private val flickrApiClient: FlickrApiClient) {


    suspend fun fetchPhotos():LiveData<List<Photo>>{
        return flickrApiClient.getPhotos()
    }

    suspend fun searchPhotos(query:String):LiveData<List<Photo>>{
        return flickrApiClient.searchPhotos(query)
    }

}