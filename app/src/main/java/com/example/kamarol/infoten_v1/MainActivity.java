package com.example.kamarol.infoten_v1;

import android.app.Fragment;
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

import com.example.kamarol.infoten_v1.Functions.ParseTimetable;
import com.example.kamarol.infoten_v1.MenuFragments.ExaminationFragment;
import com.example.kamarol.infoten_v1.MenuFragments.LedgerFragment;
import com.example.kamarol.infoten_v1.MenuFragments.ScorunFragment;
import com.example.kamarol.infoten_v1.MenuFragments.SearchSubjectFragment;
import com.example.kamarol.infoten_v1.MenuFragments.Timetable.LecturerDetailsFragment;
import com.example.kamarol.infoten_v1.MenuFragments.Timetable.SubjectDetailsFragment;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Communicator {
    NavigationView navigationView;
    LoginFragment loginFragment;
    FragmentTransaction ft;
    TextView name, id;
    View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginFragment = new LoginFragment();//INITIALIZING NEW FRAGMENT (LOGIN)

        //SYNTAX TO SHOW THE (LOGIN) FRAGMENT
        ft = getFragmentManager().beginTransaction();
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
            setTitle("Scorun");
            ScorunFragment fragment = new ScorunFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Scorun");
            fragmentTransaction.commit();
        }else if (id == R.id.search) {
            setTitle("Search Subject");
            SearchSubjectFragment fragment = new SearchSubjectFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment, "Search");
            fragmentTransaction.commit();
        } else if (id == R.id.logout) {
            SharedPreferences sharedPreferences = getSharedPreferences(LoginFragment.MyPREFERENCES, Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            loginFragment.show(ft, "dialog");
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setTitle("Timetable");
        TimetableFragment fragment = new TimetableFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, "Timetable");
        fragmentTransaction.commit();

        new ParseTimetable(this).execute(LoginFragment.username, LoginFragment.password);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.timetable);
        navigationView.setNavigationItemSelectedListener(this);

        headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.name);
        id = headerView.findViewById(R.id.id);

        name.setText(LoginFragment.NAME);
        id.setText(LoginFragment.username.toUpperCase());
    }
    public void dismissLogin(){
        loginFragment.dismiss();
    }

    @Override
    public void onTableLoad() {
        Fragment frg = getFragmentManager().findFragmentByTag("Timetable");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }

    @Override
    public void showSubjectDetails(String subject) {
        SubjectDetailsFragment fragment = new SubjectDetailsFragment().newInstance(subject);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragment.show(ft, "Subject details");
    }
    @Override
    public void showLecturerDetails(String lecturer) {
        LecturerDetailsFragment fragment = new LecturerDetailsFragment().newInstance(lecturer);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        fragment.show(ft, "Lecturer details");
    }
}
