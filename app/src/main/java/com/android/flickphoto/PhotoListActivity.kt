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

        }

    }


}
