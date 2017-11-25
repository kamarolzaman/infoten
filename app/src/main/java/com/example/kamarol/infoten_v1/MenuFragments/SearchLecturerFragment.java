package com.example.kamarol.infoten_v1.MenuFragments;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetLecturer;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.MenuFragments.Timetable.SubjectDetailsFragment;
import com.example.kamarol.infoten_v1.R;
import com.example.kamarol.infoten_v1.Tools.Lecturer;
import com.example.kamarol.infoten_v1.Tools.SubjectData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchLecturerFragment extends Fragment implements LoaderChecker{
    ArrayAdapter arrayAdapter;
    ArrayList <String> lecturers = new ArrayList<>();
    ListView result;
    Button button;
    EditText editText;
    String lecturer;

    public SearchLecturerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_lecturer, container, false);
        result = view.findViewById(R.id.result);
        arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1,lecturers);
        result.setAdapter(arrayAdapter);
        button = view.findViewById(R.id.search);
        editText = view.findViewById(R.id.lecturer);
        button.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON
            @Override
            public void onClick(View v) {
                lecturer = editText.getText().toString();
                new GetLecturer(SearchLecturerFragment.this,lecturer).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                /*
                SubjectDetailsFragment fragment = new SubjectDetailsFragment().newInstance(lecturer);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.result, fragment, "Search Result");
                fragmentTransaction.commit();
                */
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onLoad(String html) {
        lecturers.clear();
        System.out.println("Onload");
        for (int i = 0; i < GetLecturer.lecturer.size(); i++) {
            lecturers.add(GetLecturer.lecturer.get(i).getName());
        }
        GetLecturer.lecturer = null;
        arrayAdapter.notifyDataSetChanged();

    }
}
