package com.example.kamarol.infoten_v1;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        new GetTimetable().execute("", "");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Button button = (Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new AuthenticateNTLM().execute("", "", "http://info.uniten.edu.my/info/Ticketing.ASP?WCI=Biodata");//TODO
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
