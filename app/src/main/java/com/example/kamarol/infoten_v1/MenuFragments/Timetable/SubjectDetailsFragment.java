package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.app.DialogFragment;
import android.app.FragmentManager;
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
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetSection;
import com.example.kamarol.infoten_v1.Functions.GetUniqueTables;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectDetailsFragment extends DialogFragment implements LoaderChecker{
    public static ArrayList<String> sectionList = new ArrayList<>();
    String subject;
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
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
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
        Bundle args = getArguments();
        if (args!=null) {
            if (!args.getString("SUBJECT_KEY", "").equals("")) {
                subject = args.getString("SUBJECT_KEY", "");
                new GetSection(subject, SubjectDetailsFragment.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new GetUniqueTables(subject, SubjectDetailsFragment.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

    @Override
    public void onLoad(String html) {
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
            return new SectionTablesFragment().newInstance(sectionList.get(position), subject);
        }

        @Override
        public int getCount() {
            return sectionList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + sectionList.get(position);
        }
    }

}
