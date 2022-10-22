package com.aritra.gifbucket.presentation.activities

import android.os.Bundle
import android.view.Menu
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aritra.gifbucket.GifBucketApplication
import com.aritra.gifbucket.R
import com.aritra.gifbucket.data.remote.network.GifAPI
import com.aritra.gifbucket.data.utils.Status
import com.aritra.gifbucket.databinding.ActivityContainerBinding
import com.aritra.gifbucket.presentation.viewmodels.factories.GifSearchVMFactory
import com.aritra.gifbucket.presentation.viewmodels.vm.GifSearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContainerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityContainerBinding

    @Inject
    lateinit var factory: GifSearchVMFactory
    private lateinit var viewModel: GifSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarContainer.toolbar)
        binding.appBarContainer.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_container)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // coded by me now
        GifBucketApplication.appInstance.appComponent.inject(this)
        viewModel = ViewModelProvider(this,factory).get(GifSearchViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.container, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch{
           viewModel.getGifSearchResult("beach").observe(this@ContainerActivity){
               when(it.status){
                   Status.SUCCESS ->{
                       Toast.makeText(this@ContainerActivity,"successfully loaded ${it.data?.gifData?.size} number of data",Toast.LENGTH_SHORT).show()
                   }
                   Status.ERROR ->{
                       Toast.makeText(this@ContainerActivity,"ERROR! ${it.message}",Toast.LENGTH_SHORT).show()
                   }
                   Status.LOADING ->{
                       Toast.makeText(this@ContainerActivity,"loading.....",Toast.LENGTH_SHORT).show()
                   }
               }
           }
        }
    }
}