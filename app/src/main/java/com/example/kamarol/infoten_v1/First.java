package com.example.kamarol.infoten_v1;


import android.content.SharedPreferences;
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
    Button button;

    public First() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        button= view.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON
            @Override
            public void onClick(View v) {
                //new SendTable().execute();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ArrayList<String> listItems = new ArrayList();
        ArrayAdapter<String> adapter;

            adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, listItems);

        ListView carInfoList = (ListView) getActivity().findViewById(R.id.timtableList);

        new GetTimetable(carInfoList,listItems,adapter).execute(LoginFragment.username, LoginFragment.password);//todo
        super.onActivityCreated(savedInstanceState);
    }
}
