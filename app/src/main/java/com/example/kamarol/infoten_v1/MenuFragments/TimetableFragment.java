package com.example.kamarol.infoten_v1.MenuFragments;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamarol.infoten_v1.MenuFragments.Timetable.TablesFragment;
import com.example.kamarol.infoten_v1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimetableFragment extends Fragment implements CheckView{
    public static TimetableFragment context;
    ViewPager viewPager = null;
    TabLayout tabLayout;
    MyAdapter myAdapter;

    public TimetableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context = TimetableFragment.this;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        viewPager = view.findViewById(R.id.pager);
        FragmentManager fragmentManager = getChildFragmentManager();
        myAdapter = new MyAdapter(fragmentManager);
        viewPager.setAdapter(myAdapter);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.app.Fragment getItem(int position) {
            Fragment fragment = null;
            if (position==0){
                fragment = new TablesFragment().newInstance(0);
            }else if (position==1){
                fragment = new TablesFragment().newInstance(1);
            }else if (position==2){
                fragment = new TablesFragment().newInstance(2);
            }else if (position==3){
                fragment = new TablesFragment().newInstance(3);
            }else if (position==4){
                fragment = new TablesFragment().newInstance(4);
            }else if (position==5){
                fragment = new TablesFragment().newInstance(5);
            }else if (position==6){
                fragment = new TablesFragment().newInstance(6);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position==0){
                return "Monday";
            }else if (position==1){
                return "Tuesday";
            }else if (position==2){
                return "Wednesday";
            }else if (position==3){
                return "Thursday";
            }else if (position==4){
                return "Friday";
            }else if (position==5){
                return "Saturday";
            }else if (position==6){
                return "Sunday";
            }
            return "";
        }
    }

    @Override
    public int getCurrentItem(){
        return viewPager.getCurrentItem();
    }
}
