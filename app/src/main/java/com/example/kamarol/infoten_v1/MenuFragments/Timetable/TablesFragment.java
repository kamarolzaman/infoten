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

import com.example.kamarol.infoten_v1.Functions.GetTimetable;
import com.example.kamarol.infoten_v1.R;
import com.example.kamarol.infoten_v1.Subject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablesFragment extends Fragment {
    ArrayList<SubjectData> subjectData = new ArrayList();
    ArrayList<SubjectData> day1;
    ArrayList<SubjectData> day2;
    ArrayList<SubjectData> day3;
    ArrayList<SubjectData> day4;
    ArrayList<SubjectData> day5;
    ArrayList<SubjectData> day6;
    ArrayList<SubjectData> day7;
    ArrayAdapter<SubjectData> adapter;
    ListView tables;
    int day;
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
        registerForContextMenu(tables);

        switch (day){
            case 0:
                day1=subjectData;
                break;
            case 1:
                day2=subjectData;
                break;
            case 2:
                day3=subjectData;
                break;
            case 3:
                day4=subjectData;
                break;
            case 4:
                day5=subjectData;
                break;
            case 5:
                day6=subjectData;
                break;
            case 6:
                day7=subjectData;
                break;
        }

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.timetable_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.subjectDetails:
                System.out.println("Subject");
                break;
            case R.id.lecturerDetails:
                if (day==0 && day1.size()!=0){
                    System.out.println(day1.get(info.position).getLecturer() + " at day " + day);
                } else if (day==1 && day2.size()!=0){
                    System.out.println(day2.get(info.position).getLecturer()+ " at day " + day);
                } else if (day==2 && day3.size()!=0){
                    System.out.println(day3.get(info.position).getLecturer()+ " at day " + day);
                } else if (day==3 && day4.size()!=0){
                    System.out.println(day4.get(info.position).getLecturer()+ " at day " + day);
                } else if (day==4 && day5.size()!=0){
                    System.out.println(day5.get(info.position).getLecturer()+ " at day " + day);
                } else if (day==5 && day6.size()!=0){
                    System.out.println(day6.get(info.position).getLecturer()+ " at day " + day);
                } else if (day==6 && day7.size()!=0){
                    System.out.println(day7.get(info.position).getLecturer()+ " at day " + day);
                } else
                /*
                switch (day){
                    case 0:
                        System.out.println(day1.get(info.position).getLecturer());
                        break;
                    case 1:
                        System.out.println(day2.get(info.position).getLecturer());
                        break;
                    case 2:
                        System.out.println(day3.get(info.position).getLecturer());
                        break;
                    case 3:
                        System.out.println(day4.get(info.position).getLecturer());
                        break;
                    case 4:
                        System.out.println(day5.get(info.position).getLecturer());
                        break;
                    case 5:
                        System.out.println(day6.get(info.position).getLecturer());
                        break;
                    case 6:
                        System.out.println(day7.get(info.position).getLecturer());
                        break;
                }
                */
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
