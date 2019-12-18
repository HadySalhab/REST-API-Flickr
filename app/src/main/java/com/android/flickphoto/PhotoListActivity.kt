package com.android.flickphoto

import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.android.flickphoto.requests.ServiceGenerator
import com.android.flickphoto.requests.responses.FlickrResponse
import com.android.flickphoto.requests.responses.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


private const val TAG="PhotoListActivity"
class PhotoListActivity : BaseActivity() {
    private lateinit var testButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_photo)
        testButton = findViewById(R.id.test)
        testButton.setOnClickListener {
            testRetrofitApi()
        }

    }

    fun testRetrofitApi(){
        //creating an instance of the flickrAPI
        val flickrApi = ServiceGenerator.flickrApi


        //testing the search request
        //val responseCall = flickrApi.searchPhotos(text = "Earth")

        //testing getRecentPhoto request
        val responseCall = flickrApi.getRecentPhotos()

        responseCall.enqueue(object:Callback<FlickrResponse>{
            override fun onFailure(call: Call<FlickrResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<FlickrResponse>, response: Response<FlickrResponse>) {
                    Log.d(TAG,"onResponse: server response received: ${response}")
                    //200 is an http code that indicates that the response was successful
                    if(response.code()==200){
                        Log.d(TAG,"onResponse ${response.body()}")
                        val flickrResponse = response.body()
                        val photoResponse = flickrResponse?.photos
                        val listOfPhotos = photoResponse?.listOfPhoto


                        listOfPhotos?.forEach { photo->
                            Log.d(TAG,"onResponse ${photo.title}")
                        }
                    }
                    else {
                        try {
                            Log.d(TAG,"onResponse ${response.errorBody()}")
                        }
                        catch (e:IOException){
                            e.printStackTrace()
                        }
                    }
        }


        })
    }
}
