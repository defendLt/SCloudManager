package com.platdmit.simplecloudmanager.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.app.helpers.UiVisibilityStatus
import com.platdmit.simplecloudmanager.app.vm.MainViewModel
import com.platdmit.simplecloudmanager.app.vm.factory.MainActivityViewModelFactory
import com.platdmit.simplecloudmanager.domain.SCMApp
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(), UiVisibilityStatus {
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    MainActivityViewModelFactory(SCMApp.actualApiKeyService)
            ).get(MainViewModel::class.java)
        }
        toolbarInit()
        navigationInit()
    }

    private fun toolbarInit() {
        setSupportActionBar(toolbar)
    }

    private fun navigationInit() {
        mNavController = Navigation.findNavController(this, R.id.hostFragment)
        mAppBarConfiguration = AppBarConfiguration.Builder(
                R.id.domainListFragment, R.id.serverListFragment, R.id.loginFragment)
                .build()
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration)
        NavigationUI.setupWithNavController(bottom_nav, mNavController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.hostFragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp())
    }

    override fun setVisibilityToolbar(status: Boolean) {
        if (status) {
            toolbar.visibility = View.VISIBLE
        } else toolbar.visibility = View.GONE
    }

    override fun setVisibilityNavigation(status: Boolean) {
        if (status) {
            bottom_nav.visibility = View.VISIBLE
        } else bottom_nav.visibility = View.GONE
    }
}