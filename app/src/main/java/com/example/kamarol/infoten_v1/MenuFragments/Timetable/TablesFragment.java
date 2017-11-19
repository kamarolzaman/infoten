package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetTimetable;
import com.example.kamarol.infoten_v1.R;
import com.example.kamarol.infoten_v1.Subject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablesFragment extends Fragment {
    ArrayList<SubjectData> subjectData = new ArrayList();
    ArrayAdapter<SubjectData> adapter;
    ListView tables;
    int day;
    TextView textView;
    public static TablesFragment newInstance(int day) {
        TablesFragment f = new TablesFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("day", day);
        f.setArguments(args);
        return f;
    }
    public TablesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables, container, false);
        subjectData.clear();
        Bundle args = getArguments();
        day = args.getInt("day", 0);

        tables = view.findViewById(R.id.tables);
        adapter = new TableAdapter(view.getContext(), R.layout.item_class, subjectData);
        tables.setAdapter(adapter);
        int length =  GetTimetable.subject.length;
        for (int i = 0; i < length; i++) {
            if (GetTimetable.subject[i]!=null){
                if (GetTimetable.subject[i].getDay()==day){
                    subjectData.add(new SubjectData(GetTimetable.subject[i].getName(),"",GetTimetable.subject[i].getLecturer(),GetTimetable.subject[i].getLoc(),GetTimetable.subject[i].getStartTime(),GetTimetable.subject[i].getEndTime()));
                }
            }
        }
        adapter.notifyDataSetChanged();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //textView.setText(String.valueOf(day));
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
