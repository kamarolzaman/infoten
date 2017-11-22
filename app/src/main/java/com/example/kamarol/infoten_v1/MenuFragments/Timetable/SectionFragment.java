package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Communicator;
import com.example.kamarol.infoten_v1.Functions.ParseTimetable;
import com.example.kamarol.infoten_v1.MenuFragments.CheckView;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;
import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends Fragment {
    ArrayList<String> sectionList;
    ArrayList<SubjectData> subjectData = new ArrayList();
    ArrayAdapter<SubjectData> adapter;
    ListView tables;
    int section;
    public static SectionFragment newInstance(int section) {
        SectionFragment f = new SectionFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("section", section);
        f.setArguments(args);
        return f;
    }
    public SectionFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        sectionList = GetSection.section;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        Bundle args = getArguments();
        section = args.getInt("section", 0);
        tables = view.findViewById(R.id.tables);
        adapter = new TableAdapter(view.getContext(), R.layout.item_class, subjectData);
        tables.setAdapter(adapter);
        int length =  ParseTimetable.subject.length;
        for (int i = 0; i < length; i++) {
            if (ParseTimetable.subject[i]!=null){
                if (ParseTimetable.subject[i].getDay()==section){
                    subjectData.add(new SubjectData(ParseTimetable.subject[i].getName(),"", ParseTimetable.subject[i].getLecturer(), ParseTimetable.subject[i].getLoc(), ParseTimetable.subject[i].getStartTime(), ParseTimetable.subject[i].getEndTime()));
                }
            }
        }
        adapter.notifyDataSetChanged();
        return view;
    }

}
