package com.example.kamarol.infoten_v1;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Communicator {
    LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = new LoginFragment();//INITIALIZING NEW FRAGMENT (LOGIN)

        //SYNTAX TO SHOW THE (LOGIN) FRAGMENT
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        SharedPreferences sharedPreferences = getSharedPreferences(LoginFragment.MyPREFERENCES, Context.MODE_PRIVATE);
        String check = sharedPreferences.getString(LoginFragment.Username,"empty");
        System.out.println(check);
        if (check.equals("empty")){
            loginFragment.show(ft, "dialog");//SHOWS THE DIALOG -----> GOTO LoginFragment.java
        }else{
            LoginFragment.username= sharedPreferences.getString(LoginFragment.Username,"empty");
            LoginFragment.password=sharedPreferences.getString(LoginFragment.Password,"empty");
            LoginFragment.NAME=sharedPreferences.getString(LoginFragment.Name,"empty");
            showHome();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.first_fragment) {
            setTitle("First Fragment");
            First fragment = new First();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Fragment One");
            fragmentTransaction.commit();
        } else if (id == R.id.second_fragment) {
            setTitle("Welcome");
            Home fragment = new Home();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Fragment One");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void dismissDialog() {
        this.onBackPressed();
    }

    @Override
    public void showHome() {
        setTitle("Welcome");
        Home fragment = new Home();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, "Fragment One");
        fragmentTransaction.commit();
    }
    public void dismissLogin(){
        loginFragment.dismiss();
    }
}
