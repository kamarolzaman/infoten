package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetSubject;
import com.example.kamarol.infoten_v1.Functions.ParseTimetable;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.MenuFragments.SearchSubjectFragment;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;
import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectDetailsFragment extends DialogFragment implements LoaderChecker{
    public static ArrayList<String> sectionList = new ArrayList<>();
    View view;
    TextView textView;
    ViewPager viewPager = null;
    TabLayout tabLayout;
    MyAdapter myAdapter;
    public SubjectDetailsFragment() {
        setCancelable(true);
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        super.onCreate(savedInstanceState);
    }

    public static SubjectDetailsFragment newInstance(String subject) {
        SubjectDetailsFragment f = new SubjectDetailsFragment();
        Bundle args = new Bundle();
        args.putString("SUBJECT_KEY", subject);
        f.setArguments(args);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_subject_details, container, false);
        textView = view.findViewById(R.id.result);
        Bundle args = getArguments();
        if (args!=null) {
            if (!args.getString("SUBJECT_KEY", "").equals("")) {
                searchSubject(args.getString("SUBJECT_KEY", ""));
            }
        }
        if (GetSection.section!=null){
            sectionList = GetSection.section;
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void searchSubject(String subject) {
        //new GetSubject(textView,getActivity(),subject).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new GetSection(subject, SubjectDetailsFragment.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onLoad() {
        viewPager = view.findViewById(R.id.pager);
        FragmentManager fragmentManager = getChildFragmentManager();
        myAdapter = new MyAdapter(fragmentManager, sectionList);
        viewPager.setAdapter(myAdapter);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MyAdapter extends FragmentPagerAdapter {
        ArrayList<String> sectionList;

        public MyAdapter(FragmentManager fm, ArrayList<String> sectionList) {
            super(fm);
            this.sectionList = sectionList;
        }

        @Override
        public android.app.Fragment getItem(int position) {
            return new SectionFragment().newInstance(position);
        }

        @Override
        public int getCount() {
            return sectionList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sectionList.get(position);
        }
    }

}
