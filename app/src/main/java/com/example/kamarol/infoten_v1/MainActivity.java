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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetTimetable;
import com.example.kamarol.infoten_v1.MenuFragments.ExaminationFragment;
import com.example.kamarol.infoten_v1.MenuFragments.LedgerFragment;
import com.example.kamarol.infoten_v1.MenuFragments.ScorunFragment;
import com.example.kamarol.infoten_v1.MenuFragments.SearchSubjectFragment;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Communicator {
    LoginFragment loginFragment;
    TextView name;
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
            try {
                new GetTimetable(this).execute(LoginFragment.username, LoginFragment.password).get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
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

        View headerView = navigationView.getHeaderView(0);
        TextView name = headerView.findViewById(R.id.name);
        TextView id = headerView.findViewById(R.id.id);
        name.setText(LoginFragment.NAME);
        id.setText(LoginFragment.username.toUpperCase());

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
        if (id == R.id.timetable) {
            setTitle("Timetable");
            TimetableFragment fragment = new TimetableFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Timetable");
            fragmentTransaction.commit();
        } else if (id == R.id.ledger) {
            setTitle("Ledger Balance");
            LedgerFragment fragment = new LedgerFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Ledger");
            fragmentTransaction.commit();
        } else if (id == R.id.examination) {
            setTitle("Examination");
            ExaminationFragment fragment = new ExaminationFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Examination");
            fragmentTransaction.commit();
        } else if (id == R.id.scorun) {
            setTitle("Examination");
            ScorunFragment fragment = new ScorunFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Scorun");
            fragmentTransaction.commit();
        } else if (id == R.id.logout) {
            setTitle("Logout");
            SearchSubjectFragment fragment = new SearchSubjectFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Search");
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
        setTitle("Timetable");
        TimetableFragment fragment = new TimetableFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, "Timetable");
        fragmentTransaction.commit();
    }
    public void dismissLogin(){
        loginFragment.dismiss();
    }
}
