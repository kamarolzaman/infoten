package com.example.kamarol.infoten_v1.MenuFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Communicator;
import com.example.kamarol.infoten_v1.LoginFragment;
import com.example.kamarol.infoten_v1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LedgerFragment extends Fragment {
    TextView welcome, name;
    public LedgerFragment() {
        // Required empty public constructor
    }
    Communicator comm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);
        welcome = view.findViewById(R.id.welcome);
        Button button = view.findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON
            @Override
            public void onClick(View v) {//WHEN THE EMBEDED LISTENER GET AN onClick, IT WILL RUN THIS METHOD
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(LoginFragment.MyPREFERENCES, Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                comm.dismissDialog();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        welcome.setText("Welcone to my k00l app "+LoginFragment.NAME);
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();

    }
}