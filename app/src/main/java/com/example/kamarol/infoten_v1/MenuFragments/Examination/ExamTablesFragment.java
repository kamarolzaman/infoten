package com.example.kamarol.infoten_v1.MenuFragments.Examination;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.MenuFragments.ExaminationFragment;
import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamTablesFragment extends Fragment {
    ArrayList<ExaminationData> examinationData = new ArrayList();
    ArrayAdapter<ExaminationData> adapter;
    ListView tables;
    int day, group;
    TextView textView;
    public static ExamTablesFragment newInstance(int day, int group) {
        ExamTablesFragment f = new ExamTablesFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("day", day);
        args.putInt("group", group);
        f.setArguments(args);
        return f;
    }

    public ExamTablesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_tables, container, false);
        examinationData.clear();
        Bundle args = getArguments();
        day = args.getInt("day", 0);
        group = args.getInt("group", 0);

        tables = view.findViewById(R.id.tables);
        adapter = new ExaminationAdapter(view.getContext(), R.layout.item_class, examinationData);
        tables.setAdapter(adapter);
        for (int i = 0; i < group; i++) {
            if (day==i){
                for (ExaminationData e:ExaminationFragment.examinationDataArrayList) {
                    if (e.getGroup()==i){
                        examinationData.add(e);
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
        return view;
    }

}
