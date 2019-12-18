package com.android.flickphoto.requests.responses

import com.android.flickphoto.models.Photo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



//Both Search & Recent
class PhotosResponse {
    @SerializedName("photo")
    @Expose()
    private var _Photos: List<Photo> =  emptyList()

    val Photos : List<Photo>
        get() = _Photos

    override fun toString(): String {
        return "RecentPhotosResponse(_recentPhotos=$_Photos)"
    }


}