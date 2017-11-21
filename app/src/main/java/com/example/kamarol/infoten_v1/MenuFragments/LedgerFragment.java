package com.example.kamarol.infoten_v1.MenuFragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
    Button makePaymentBtn;
    public LedgerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ledger, container, false);
        makePaymentBtn = view.findViewById(R.id.payment);
        makePaymentBtn.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON
            @Override
            public void onClick(View v) {//WHEN THE EMBEDED LISTENER GET AN onClick, IT WILL RUN THIS METHOD
                String url = "http://"+LoginFragment.username+":"+LoginFragment.password+"@info.uniten.edu.my/info/Ticketing.ASP?WCI=LedgerBalance";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return view;
    }
}
