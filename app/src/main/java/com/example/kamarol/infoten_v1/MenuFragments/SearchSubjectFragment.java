package com.example.kamarol.infoten_v1.MenuFragments;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetSubject;
import com.example.kamarol.infoten_v1.MenuFragments.Timetable.SubjectDetailsFragment;
import com.example.kamarol.infoten_v1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchSubjectFragment extends DialogFragment{
    Button button;
    EditText editText;
    String subject;
    TextView textView;
    public SearchSubjectFragment() {
        setCancelable(true);
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        super.onCreate(savedInstanceState);
    }

    public static SearchSubjectFragment newInstance(String subject) {
        SearchSubjectFragment f = new SearchSubjectFragment();
        Bundle args = new Bundle();
        args.putString("SUBJECT_KEY", subject);
        f.setArguments(args);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_subject, container, false);
        button = view.findViewById(R.id.search);
        editText = view.findViewById(R.id.subject);
        Bundle args = getArguments();
        if (args!=null) {
            if (!args.getString("SUBJECT_KEY", "").equals("")) {
                editText.setText(args.getString("SUBJECT_KEY", ""));
                searchSubject(args.getString("SUBJECT_KEY", ""));
            }
        }
        button.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON
            @Override
            public void onClick(View v) {//WHEN THE EMBEDED LISTENER GET AN onClick, IT WILL RUN THIS METHOD
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

    public void searchSubject(String subject) {
        new GetSubject(textView,getActivity(),subject).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
