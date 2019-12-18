package com.android.flickphoto

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    override fun setContentView(layoutResID: Int) {

        val rootView = layoutInflater.inflate(R.layout.activity_base, null)
        val frameLayout = rootView.findViewById<FrameLayout>(R.id.activity_container)
        progressBar = rootView.findViewById<ProgressBar>(R.id.base_progressBar)
        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(rootView)
    }


    fun showProgressBar(visible: Boolean) {

        progressBar.setVisibility(if (visible) View.VISIBLE else View.INVISIBLE);
    }




}