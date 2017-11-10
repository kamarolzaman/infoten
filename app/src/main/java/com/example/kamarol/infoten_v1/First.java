package com.example.kamarol.infoten_v1;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment {


    public First() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ArrayList<String> listItems = new ArrayList();
        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, listItems);

        ListView carInfoList = (ListView) getActivity().findViewById(R.id.timtableList);

        new GetTimetable(carInfoList,listItems,adapter).execute(LoginFragment.username, LoginFragment.password);//todo
        super.onActivityCreated(savedInstanceState);
    }
}
