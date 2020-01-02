package com.android.flickphoto

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.flickphoto.ui.display.DisplayPhotoFragment
import com.google.android.material.appbar.AppBarLayout


private const val TAG="PhotoListActivity"
class MainActivity : AppCompatActivity() , DisplayPhotoFragment.Callbacks {
    private lateinit var toolbar:Toolbar
    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var appBarLayout:AppBarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        appBarLayout = findViewById(R.id.main_appbarlayout)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfig)
    }

    override fun showToolbar() {
        appBarLayout.setExpanded(true,true)

    }

}
