package com.example.kamarol.infoten_v1.MenuFragments.Timetable;


import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.GetLecturer;
import com.example.kamarol.infoten_v1.Functions.GetLecturerTables;
import com.example.kamarol.infoten_v1.Functions.GetSection;
import com.example.kamarol.infoten_v1.Functions.GetUniqueTables;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.MenuFragments.TimetableFragment;
import com.example.kamarol.infoten_v1.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class LecturerDetailsFragment extends DialogFragment implements LoaderChecker{
    View view;
    ViewPager viewPager = null;
    TabLayout tabLayout;
    MyAdapter myAdapter;

    int id;
    String name, phone, dept, email;
    TextView nameT, phoneT, deptT, emailT;

    public LecturerDetailsFragment() {
        // Required empty public constructor
    }
    public static LecturerDetailsFragment newInstance(String name) {
        LecturerDetailsFragment f = new LecturerDetailsFragment();
        Bundle args = new Bundle();
        args.putString("LECTURER_NAME", name);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        name = args.getString("LECTURER_NAME","");
        //System.out.println(name);
        new GetLecturer(LecturerDetailsFragment.this, name).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        view = inflater.inflate(R.layout.fragment_lecturer_details, container, false);
        nameT = view.findViewById(R.id.name);
        phoneT = view.findViewById(R.id.phone);
        deptT = view.findViewById(R.id.dept);
        emailT = view.findViewById(R.id.email);
        /*
        Bundle args = getArguments();
        id = args.getInt("LECTURER_ID", 0);
        name = args.getString("LECTURER_NAME","");
        phone = args.getString("LECTURER_PHONE","");
        dept = args.getString("LECTURER_DEPT","");
        email = args.getString("LECTURER_EMAIL","");
        */


        new GetLecturerTables(name,this).execute();
        return view;
    }

    @Override
    public void onLoad(String html) {
        id = GetLecturer.lecturer.get(0).getId();
        name = GetLecturer.lecturer.get(0).getName();
        phone = GetLecturer.lecturer.get(0).getPhone();
        dept = GetLecturer.lecturer.get(0).getDept();
        email = GetLecturer.lecturer.get(0).getEmail();

        nameT.setText(name);
        phoneT.setText(phone);
        deptT.setText(dept);
        emailT.setText(email);

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
    }


    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.app.Fragment getItem(int position) {
            return new LecturerTablesFragment().newInstance(position,name);
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
