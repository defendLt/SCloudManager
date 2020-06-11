package com.platdmit.simplecloudmanager.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.fragments.DomainFragment;
import com.platdmit.simplecloudmanager.app.helpers.UiVisibilityStatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements UiVisibilityStatus {

    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarInit();
        navigationInit();
    }

    private void toolbarInit(){
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void navigationInit(){
        mNavController = Navigation.findNavController(this, R.id.hostFragment);
        mBottomNavigationView = findViewById(R.id.bottom_nav);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.domainListFragment, R.id.serverListFragment, R.id.loginFragment)
                .build();

        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mBottomNavigationView, mNavController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.hostFragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void setVisibilityToolbar(boolean status) {
        if(status){
            mToolbar.setVisibility(View.VISIBLE);
        } else mToolbar.setVisibility(View.GONE);
    }

    @Override
    public void setVisibilityNavigation(boolean status) {
        if(status){
            mBottomNavigationView.setVisibility(View.VISIBLE);
        } else mBottomNavigationView.setVisibility(View.GONE);
    }
}
