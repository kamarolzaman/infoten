package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.os.AsyncTask;
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
import com.example.kamarol.infoten_v1.Functions.GetLecturer;
import com.example.kamarol.infoten_v1.Functions.ParseTimetable;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.MenuFragments.CheckView;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;
import com.example.kamarol.infoten_v1.R;
import com.example.kamarol.infoten_v1.Tools.SubjectData;
import com.example.kamarol.infoten_v1.Tools.TableAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablesFragment extends Fragment implements LoaderChecker {
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
        int length =  ParseTimetable.subject.length;
        for (int i = 0; i < length; i++) {
            if (ParseTimetable.subject[i]!=null){
                if (ParseTimetable.subject[i].getDay()==day){
                    subjectData.add(new SubjectData(ParseTimetable.subject[i].getName(),"", ParseTimetable.subject[i].getLecturer(), ParseTimetable.subject[i].getLoc(), ParseTimetable.subject[i].getStartTime(), ParseTimetable.subject[i].getEndTime()));
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
                    comm.showSubjectDetails(subjectData.get(info.position).getName());
                    return true;
                }
                break;
            case R.id.lecturerDetails:
                if(checkView.getCurrentItem()==day) {
                    System.out.println(subjectData.get(info.position).getLecturer()+day);
                    new GetLecturer(TablesFragment.this, subjectData.get(info.position).getLecturer()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

    @Override
    public void onLoad(String html) {
        comm.showLecturerDetails(GetLecturer.lecturer.get(0).getId(),GetLecturer.lecturer.get(0).getName(),GetLecturer.lecturer.get(0).getPhone(),GetLecturer.lecturer.get(0).getDept(),GetLecturer.lecturer.get(0).getEmail());
    }
}
