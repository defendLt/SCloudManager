package com.platdmit.simplecloudmanager

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.simplecloudmanager.databinding.ActivityMainBinding
import com.platdmit.simplecloudmanager.screens.main.MainViewModel
import com.platdmit.simplecloudmanager.utilities.UiVisibilityStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity(R.layout.activity_main), UiVisibilityStatus {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewBinding: ActivityMainBinding by viewBinding(R.id.nav_host_root)
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarInit()
        navigationInit()
    }

    private fun toolbarInit() {
        setSupportActionBar(viewBinding.toolbar)
    }

    private fun navigationInit() {
        navController = Navigation.findNavController(this, R.id.hostFragment)
        appBarConfiguration = AppBarConfiguration.Builder(
                R.id.domainListFragment, R.id.serverListFragment, R.id.loginFragment)
                .build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(viewBinding.bottomNav, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.account_data -> {
                true
            }
            R.id.settings_data -> {
                navController.navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return (NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp())
    }

    override fun setVisibilityToolbar(status: Boolean) {
        if (status) {
            viewBinding.toolbar.visibility = View.VISIBLE
        } else viewBinding.toolbar.visibility = View.GONE
    }

    override fun setVisibilityNavigation(status: Boolean) {
        if (status) {
            viewBinding.bottomNav.visibility = View.VISIBLE
        } else viewBinding.bottomNav.visibility = View.GONE
    }

    override fun setVisibilityLoader(status: Boolean) {
        if (status) {
            viewBinding.loaderHover.visibility = View.VISIBLE
        } else viewBinding.loaderHover.visibility = View.GONE
    }
}