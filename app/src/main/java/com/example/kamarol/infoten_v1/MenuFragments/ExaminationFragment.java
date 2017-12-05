package com.example.kamarol.infoten_v1.MenuFragments;


import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kamarol.infoten_v1.MenuFragments.Examination.ExamTablesFragment;
import com.example.kamarol.infoten_v1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExaminationFragment extends Fragment {
    ViewPager viewPager = null;
    TabLayout tabLayout;
    MyAdapter myAdapter;
    Button button;
    Dialog dialog;
    public ExaminationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examination, container, false);
        viewPager = view.findViewById(R.id.pager);
        FragmentManager fragmentManager = getChildFragmentManager();
        myAdapter = new MyAdapter(fragmentManager);
        viewPager.setAdapter(myAdapter);
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        button= view.findViewById(R.id.viewResult);
        dialog = new Dialog(getActivity());
        dialog.setTitle("Examination result");
        dialog.setContentView(R.layout.examination_result);
        button.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON

            @Override
            public void onClick(View v) {

                dialog.show();
            }
        });
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
                fragment = new ExamTablesFragment().newInstance(0);
            }else if (position==1){
                fragment = new ExamTablesFragment().newInstance(1);
            }else if (position==2){
                fragment = new ExamTablesFragment().newInstance(2);
            }else if (position==3){
                fragment = new ExamTablesFragment().newInstance(3);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position==0){
                return "21/12/2017";
            }else if (position==1){
                return "22/12/2017";
            }else if (position==2){
                return "25/12/2017";
            }else if (position==3){
                return "27/12/2017";
            }
            return "";
        }
    }
}
