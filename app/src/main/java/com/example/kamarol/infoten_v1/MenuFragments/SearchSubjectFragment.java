package com.example.kamarol.infoten_v1.MenuFragments;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kamarol.infoten_v1.MenuFragments.Timetable.SubjectDetailsFragment;
import com.example.kamarol.infoten_v1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSubjectFragment extends Fragment{
    Button button;
    EditText editText;
    String subject;
    public SearchSubjectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_subject, container, false);
        button = view.findViewById(R.id.search);
        editText = view.findViewById(R.id.subject);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = editText.getText().toString();
                SubjectDetailsFragment fragment = new SubjectDetailsFragment().newInstance(subject);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.result, fragment, "Search Result");
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
