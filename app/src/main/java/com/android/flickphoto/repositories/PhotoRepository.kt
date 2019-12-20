package com.android.flickphoto.repositories

import androidx.lifecycle.LiveData
import com.android.flickphoto.models.Photo
import com.android.flickphoto.requests.FlickrApiClient


class PhotoRepository (private val flickrApiClient: FlickrApiClient) {


    suspend fun fetchPhotos():List<Photo>{
        return flickrApiClient.getPhotos()
    }

    suspend fun searchPhotos(query:String):List<Photo>{
        return flickrApiClient.searchPhotos(query)
    }

}