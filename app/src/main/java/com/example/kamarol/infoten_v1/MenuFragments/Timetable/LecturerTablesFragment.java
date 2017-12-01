package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kamarol.infoten_v1.Functions.GetLecturer;
import com.example.kamarol.infoten_v1.Functions.GetLecturerTables;
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
public class LecturerTablesFragment extends Fragment {
    View view;
    ArrayList<SubjectData> subjectData = new ArrayList();
    ArrayAdapter<SubjectData> adapter;
    ListView tables;
    String lecturer;
    int day;
    public static LecturerTablesFragment newInstance(int day, String lecturer) {
        LecturerTablesFragment f = new LecturerTablesFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("day", day);
        args.putString("lecturer", lecturer);
        f.setArguments(args);
        return f;
    }
    public LecturerTablesFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lecturer_tables, container, false);
        subjectData.clear();
        Bundle args = getArguments();
        lecturer = args.getString("lecturer", "");
        day = args.getInt("day", 0);
        for (int i = 0; i < GetLecturerTables.lecturerTables.size(); i++) {
            System.out.println(day);
            System.out.println("DAY " + GetLecturerTables.lecturerTables.get(i).getDay());
            if (GetLecturerTables.lecturerTables.get(i).getDay() == day) {
                System.out.println("######################");
                String name = GetLecturerTables.lecturerTables.get(i).getName();
                String code = "";
                String lecturer = GetLecturerTables.lecturerTables.get(i).getLecturer();
                String loc = GetLecturerTables.lecturerTables.get(i).getLoc();
                int start = GetLecturerTables.lecturerTables.get(i).getStartTime();
                int end = GetLecturerTables.lecturerTables.get(i).getEndTime();
                int day = GetLecturerTables.lecturerTables.get(i).getDay();
                System.out.println(name);
                System.out.println(lecturer);
                System.out.println(loc);
                System.out.println(start);
                System.out.println(end);
                System.out.println(day);
                subjectData.add(new SubjectData(name,code,lecturer,loc,start,end,day));
            }
        }
        //subjectData = GetUniqueTables.uniqueSubject;
        tables = view.findViewById(R.id.tables);
        adapter = new TableAdapter(view.getContext(), R.layout.item_class, subjectData);
        tables.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}
