package com.android.flickphoto.requests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.flickphoto.models.Photo
import com.android.flickphoto.requests.responses.FlickrResponse
import com.android.flickphoto.util.NETWORK_TIMEOUT
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import java.io.IOException


private const val TAG = "FlickrApiClient"

class FlickrApiClient {

    //creating an instance of the flickrAPI
    val flickrApi = ServiceGenerator.flickrApi

    suspend fun getPhotos():LiveData<List<Photo>>{
        return fetchPhotoMetaData(flickrApi.getRecentPhotos())
    }

    suspend fun searchPhotos(query:String):LiveData<List<Photo>>{
        return fetchPhotoMetaData(flickrApi.searchPhotos(text=query))
    }


    suspend fun fetchPhotoMetaData(flickrRequest:Deferred<Response<FlickrResponse?>>): LiveData<List<Photo>> {
        val responseLiveData: MutableLiveData<List<Photo>> = MutableLiveData()


        try {
            withTimeout(NETWORK_TIMEOUT) {
                try {
                    //getting the response from the http request
                    val response = flickrRequest.await()

                    //if response was successful and OK (CODE=200)
                    if (response.code() == 200) {
                        val flickrResponse = response.body()
                        val photoResponse = flickrResponse?.photos
                        val listOfPhotos = photoResponse?.listOfPhoto ?: mutableListOf()
                        val listOfPhotosWithUrl = listOfPhotos.filterNot {
                            it.url.isBlank()
                        }
                       responseLiveData.value = listOfPhotosWithUrl
                    } else {
                        //if response was successful but not OK(CODE!=200)
                        try {
                            Log.d(TAG, "onResponse ${response.errorBody()}")
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
                // catching the exception if the http request is failed
                catch (e: Exception) {
                    Log.e(TAG, "Failed to fetch photos", e)

                }
            }

        }

        //catching the timeoutCancellation exception to inform the user
        catch (e: TimeoutCancellationException) {
            Log.e(TAG, "We got a timeoutCancellationException")
        }

        return responseLiveData

    }


}