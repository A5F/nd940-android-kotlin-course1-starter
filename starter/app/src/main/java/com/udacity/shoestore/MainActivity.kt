package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    fun getMainViewModel(): MainViewModel{
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.toolbar.title = getString(R.string.app_name)
        Timber.plant(Timber.DebugTree())

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val appBarConfiguration = AppBarConfiguration.Builder()
            .build()

        NavigationUI.setupWithNavController(activityMainBinding.toolbar, findNavController(R.id.nav_host_fragment),appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.logout) {
            findNavController(R.id.nav_host_fragment)
            return true
        } else super.onOptionsItemSelected(item)

    }

}
