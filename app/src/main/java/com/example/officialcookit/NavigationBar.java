package com.example.officialcookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class NavigationBar extends AppCompatActivity {

    private AppBaConfiguration mAppBarConfiguration;
    Button callAddDetails;

    //-------call to AddDetails
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        callAddDetails = findViewById(R.id.nav_profile);
        callAddDetails.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationBar.this,AddDetails.class);
                startActivity(intent);
            }
        });
        //------end Adddetail calling

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_profile:
                        Toast.makeText(getApplicationContext(),"Loading your Profile",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_recipefeed:
                        Toast.makeText(getApplicationContext(),"Loading Recipe Feed",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_addpost:
                        Toast.makeText(getApplicationContext(),"Loading add posts",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_walk:
                        Toast.makeText(getApplicationContext(),"Starting to count steps",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_measure:
                        Toast.makeText(getApplicationContext(),"Calculating",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.nav_logout:
                        Toast.makeText(getApplicationContext(),"Loging out",Toast.LENGTH_LONG).show();
                        break;
                }

                drawer.closeDrawers();

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}