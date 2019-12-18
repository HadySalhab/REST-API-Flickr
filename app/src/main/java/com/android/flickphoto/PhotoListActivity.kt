package com.android.flickphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
