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

import com.example.kamarol.infoten_v1.Communicator;
import com.example.kamarol.infoten_v1.Functions.GetTimetable;
import com.example.kamarol.infoten_v1.MenuFragments.CheckView;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;
import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablesFragment extends Fragment {
    Communicator comm;
    CheckView checkView;

    ArrayList<SubjectData> subjectData = new ArrayList();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //searchSubject = (SearchSubject) getActivity();
        comm = (Communicator) getActivity();
        checkView = (CheckView) TimetableFragment.context ;
        super.onActivityCreated(savedInstanceState);
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
        System.out.println("Selected");
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.subjectDetails:
                if(checkView.getCurrentItem()==day) {
                    System.out.println(subjectData.get(info.position).getName());
                    comm.showSearchFrag(subjectData.get(info.position).getName());
                    return true;
                }
                break;
            case R.id.lecturerDetails:
                if(checkView.getCurrentItem()==day) {
                    System.out.println(subjectData.get(info.position).getLecturer()+day);
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
