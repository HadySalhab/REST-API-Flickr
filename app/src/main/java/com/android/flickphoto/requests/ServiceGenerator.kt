package com.android.flickphoto.requests

import com.android.flickphoto.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val retrofitBuilder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    //retrofit knows how to create an instance of the flickrApi interface
    val flickrApi = retrofit.create(FlickrApi::class.java)

}