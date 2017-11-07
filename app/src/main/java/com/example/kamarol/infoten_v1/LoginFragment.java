package com.example.kamarol.infoten_v1;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment {
    Button btn;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SET THE THE STYLING OF THE FRAGMENT TO NO FRAME
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //ASSOCIATE LoginFragment TO fragment_login.xml -VVVV-
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn=(Button)view.findViewById(R.id.loginButton);

        //YOU CAN'T SET ONCLICK ON xml FILE TO RUN A METHOD HERE
        btn.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON

            @Override
            public void onClick(View v) {//WHEN THE EMBEDED LISTENER GET AN onClick, IT WILL RUN THIS METHOD
                // TODO Auto-generated method stub
                getDialog().dismiss();//DISMISS THE CURRENT DIALOG
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // PUT DECLARATION OR WHATEVER HERE

    }

}
