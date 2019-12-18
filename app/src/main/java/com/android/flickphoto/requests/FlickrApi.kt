package com.android.flickphoto.requests

import com.android.flickphoto.requests.responses.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("services/rest")
    fun searchPhotos(
        @Query("api_key") apiKey: String = "75ec5012a10f8767d1716ce6082843de",
        @Query("method") method: String = "flickr.photos.search",
        @Query("text") text: String,
        @Query("nojsoncallback") noJsonCallBack: String = "1",
        @Query("format") responseFormat: String = "json",
        @Query("extras") extras: String = "url_s,date_taken,owner_name"
    ): Call<PhotosResponse>

    @GET("services/rest")
    fun getRecentPhotos(
        @Query("api_key") apiKey: String = "75ec5012a10f8767d1716ce6082843de",
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("nojsoncallback") noJsonCallBack: String = "1",
        @Query("format") responseFormat: String = "json",
        @Query("extras") extras: String = "url_s,date_taken,owner_name"
    ): Call<PhotosResponse>

}