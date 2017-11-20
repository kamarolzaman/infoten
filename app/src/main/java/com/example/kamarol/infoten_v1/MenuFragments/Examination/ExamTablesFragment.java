package com.example.kamarol.infoten_v1.MenuFragments.Examination;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamTablesFragment extends Fragment {
    ArrayList<ExaminationData> examinationData = new ArrayList();
    ArrayAdapter<ExaminationData> adapter;
    ListView tables;
    int day;
    TextView textView;
    public static ExamTablesFragment newInstance(int day) {
        ExamTablesFragment f = new ExamTablesFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("day", day);
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

        tables = view.findViewById(R.id.tables);
        adapter = new ExaminationAdapter(view.getContext(), R.layout.item_class, examinationData);
        tables.setAdapter(adapter);

        if (day==0){
            examinationData.add(new ExaminationData("Java Programming","CSEB534","01A","B187","Level 6, Library",1,5));
        }else if (day==1){
            examinationData.add(new ExaminationData("Software Project Management","CSEB344","01B","B256","Level 6, Library",3,6));
        }else if (day==2){
            examinationData.add(new ExaminationData("Java Programming","CSEB534","01A","B187","Level 6, Library",1,5));
            examinationData.add(new ExaminationData("Programming Language","CSEB313","02B","B091","Level 6, Library",7,10));
        }else if (day==3){
            examinationData.add(new ExaminationData("Islamic and Civil Society III","ICTB312","01A","B187","Level 6, Library",1,5));
        }
        adapter.notifyDataSetChanged();
        return view;
    }

}
