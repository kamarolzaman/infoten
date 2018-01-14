package com.example.kamarol.infoten_v1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.LinkedList;

public class ExaminationResultTestUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_result_test_ui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView rvGpaResults = (RecyclerView) findViewById(R.id.rvExamCards);

        // Initialize the list
        LinkedList<GPA_Result> gpa_results = new LinkedList<>();
        for (int i=0; i<5; i++) {
            gpa_results.add(new GPA_Result(Integer.toString(i), "2017", "4", "4"));
        }
        // Create adapter passing in the sample user data
        CgpaResultsAdapter adapter = new CgpaResultsAdapter(this, gpa_results);
        // Attach the adapter to the recyclerview to populate items
        rvGpaResults.setAdapter(adapter);
        // Set layout manager to position the items
        rvGpaResults.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

}
