package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kamarol.infoten_v1.Functions.GetUniqueTables;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.R;
import com.example.kamarol.infoten_v1.Tools.SectionTableAdapter;
import com.example.kamarol.infoten_v1.Tools.SubjectData;
import com.example.kamarol.infoten_v1.Tools.TableAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionTablesFragment extends Fragment implements LoaderChecker{
    View view;
    ArrayList<SubjectData> subjectData = new ArrayList();
    ArrayAdapter<SubjectData> adapter;
    ListView tables;
    String section, subject;
    public static SectionTablesFragment newInstance(String section, String subject) {
        SectionTablesFragment f = new SectionTablesFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("section", section);
        args.putString("subject", subject);
        f.setArguments(args);
        return f;
    }
    public SectionTablesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tables, container, false);
        subjectData.clear();
        Bundle args = getArguments();
        section = args.getString("section", "");
        subject = args.getString("subject", "");
        System.out.println(GetUniqueTables.uniqueSubject2);
        if(GetUniqueTables.uniqueSubject2==null) {
            System.out.println("NULL isit");
        }else{
            for (int i = 0; i < GetUniqueTables.uniqueSubject2.size(); i++) {
                if(GetUniqueTables.uniqueSubject2.get(i).getSection().equals(section)){
                    subjectData.add(new SubjectData(GetUniqueTables.uniqueSubject2.get(i).getName(),"", GetUniqueTables.uniqueSubject2.get(i).getLecturer(), GetUniqueTables.uniqueSubject2.get(i).getLoc(), GetUniqueTables.uniqueSubject2.get(i).getStartTime(), GetUniqueTables.uniqueSubject2.get(i).getEndTime(), GetUniqueTables.uniqueSubject2.get(i).getDay()));
                }
            }
            //subjectData = GetUniqueTables.uniqueSubject;
            tables = view.findViewById(R.id.tables);
            adapter = new SectionTableAdapter(view.getContext(), R.layout.item_class, subjectData);
            tables.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onLoad(String html) {
        subjectData.clear();
        //subjectData = GetUniqueTables.uniqueSubject;
        tables = view.findViewById(R.id.tables);
        adapter = new TableAdapter(view.getContext(), R.layout.item_class, subjectData);
        tables.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

