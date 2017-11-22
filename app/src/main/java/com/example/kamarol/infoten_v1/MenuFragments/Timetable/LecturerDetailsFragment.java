package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;
import com.example.kamarol.infoten_v1.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class LecturerDetailsFragment extends DialogFragment {
    View view;
    ViewPager viewPager = null;
    TabLayout tabLayout;
    MyAdapter myAdapter;
    public LecturerDetailsFragment() {
        // Required empty public constructor
    }
    public static LecturerDetailsFragment newInstance(String subject) {
        LecturerDetailsFragment f = new LecturerDetailsFragment();
        Bundle args = new Bundle();
        args.putString("LECTURER_KEY", subject);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lecturer_details, container, false);
        viewPager = view.findViewById(R.id.pager);
        FragmentManager fragmentManager = getChildFragmentManager();
        myAdapter = new MyAdapter(fragmentManager);
        viewPager.setAdapter(myAdapter);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        if(sdf.format(d).equals("Monday")){viewPager.setCurrentItem(0);}
        else if(sdf.format(d).equals("Tuesday")){viewPager.setCurrentItem(1);}
        else if(sdf.format(d).equals("Wednesday")){viewPager.setCurrentItem(2);}
        else if(sdf.format(d).equals("Thursday")){viewPager.setCurrentItem(3);}
        else if(sdf.format(d).equals("Friday")){viewPager.setCurrentItem(4);}
        else if(sdf.format(d).equals("Saturday")){viewPager.setCurrentItem(5);}
        else if(sdf.format(d).equals("Sunday")){viewPager.setCurrentItem(6);}
        return view;
    }
    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.app.Fragment getItem(int position) {
            return new TablesFragment().newInstance(position);
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
}
