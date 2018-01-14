package com.example.kamarol.infoten_v1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamarol.infoten_v1.Functions.ParseAdvising;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class ExaminationResultTestUI extends DialogFragment implements LoaderChecker {
    List<SubjectResult> resultList = new ArrayList<>();
    View view;
    public ExaminationResultTestUI(){
        setCancelable(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_examination_result_test_ui, container, false);
        new ParseAdvising(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR); //execute asynctask
        return view;
    }

    @Override
    public void onLoad(String html) {//called when asynctask is done
        ExaminationResult examinationResult = new ExaminationResult();
        AdvisingTableParser parser = new AdvisingTableParser();
        LinkedHashSet<HashMap> subjectResultList = parser.parseTable(html);
        int x = 0;
        for (HashMap<String, String> parserData : subjectResultList) {
            System.out.println(x++);
            // Get CGPA for each semester
            resultList.add(new SubjectResult(parserData.get("SUBJECT_CODE"), parserData.get("RESULT"), Integer.parseInt(parserData.get("SEMESTER")), Integer.parseInt(parserData.get("YEAR"))));
            //examinationResult.add(new SubjectResult(parserData.get("SUBJECT_CODE"), parserData.get("RESULT"), Integer.parseInt(parserData.get("SEMESTER")), Integer.parseInt(parserData.get("YEAR"))));
        }

        RecyclerView rvGpaResults = view.findViewById(R.id.rvExamCards);
//        LinkedList<GPA_Result> gpa_results = new LinkedList<>();// Initialize the list
//        for (int i = 0; i < 5; i++) {
//            gpa_results.add(new GPA_Result(Integer.toString(i), "2017", "4", "4"));
//        }
        CgpaResultsAdapter adapter = new CgpaResultsAdapter(view.getContext(), resultList);// Create adapter passing in the sample user data
        rvGpaResults.setAdapter(adapter);// Attach the adapter to the recyclerview to populate items
        rvGpaResults.setLayoutManager(new LinearLayoutManager(view.getContext()));// Set layout manager to position the items
        // That's all!
    }
}
