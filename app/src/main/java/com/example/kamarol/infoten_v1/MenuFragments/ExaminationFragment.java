package com.example.kamarol.infoten_v1.MenuFragments;


import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.ExaminationResultTestUI;
import com.example.kamarol.infoten_v1.Functions.ExaminationTableParser;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.LoginFragment;
import com.example.kamarol.infoten_v1.MenuFragments.Examination.ExamTablesFragment;
import com.example.kamarol.infoten_v1.MenuFragments.Examination.ExaminationData;
import com.example.kamarol.infoten_v1.MenuFragments.Timetable.SubjectDetailsFragment;
import com.example.kamarol.infoten_v1.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExaminationFragment extends Fragment implements LoaderChecker {
    public static ArrayList<ExaminationData> examinationDataArrayList = new ArrayList<>();
    ListView listView;
    ArrayAdapter <ExaminationData> myAdaper;
//    ArrayList<String> uniqueDate = new ArrayList<>();
    int group;
    View view;
//    ViewPager viewPager = null;
//    TabLayout tabLayout;
//    MyAdapter myAdapter;
    Button button;
    Dialog dialog;
    public ExaminationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_examination, container, false);

        button= view.findViewById(R.id.viewResult);
        dialog = new Dialog(getActivity());
        dialog.setTitle("Examination result");
        dialog.setContentView(R.layout.examination_result);
        TextView test = dialog.findViewById(R.id.congrat);
        test.setText("WEWEWEDADASD");
        ExaminationTableParser examinationTableParser = new ExaminationTableParser(ExaminationFragment.this, LoginFragment.username, LoginFragment.password);
        examinationTableParser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        button.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON

            @Override
            public void onClick(View v) {
                ExaminationResultTestUI fragment = new ExaminationResultTestUI();
                fragment.show(getFragmentManager(), "Subject details");
            }
        });
        listView = view.findViewById(R.id.examtableslistview);
        myAdaper = new MyAdapter(view.getContext(), R.layout.examtable_format, examinationDataArrayList);
        listView.setAdapter(myAdaper);
        myAdaper.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onLoad(String html) {

        //System.out.println(html);

        //Document doc = Jsoup.parse("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"><link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" href=\"./exam_files/styles.css\"><link rel=\"stylesheet\" media=\"print\" type=\"text/css\" href=\"./exam_files/print.css\"></head><body><h1>Exam List for Semester 2, Academic Year 2017/2018</h1><table cellspacing=\"0\"><thead><tr valign=\"TOP\"><td rowspan=\"2\">No.</td><td colspan=\"2\">Subject</td><td rowspan=\"2\">Section</td><td rowspan=\"2\">Location</td><td rowspan=\"2\">Date &amp; Time</td><td rowspan=\"2\">Seat</td></tr><tr><td>Code</td><td>Description</td></tr></thead><tbody><tr class=\"LINE1\"><td>1.</td><td>CGNB424</td><td>Project 2</td><td>SE</td><td>N/A</td><td>20/12/17 13:30:00 - 15:30:00</td><td>N/A</td></tr><tr class=\"LINE2\"><td>2.</td><td>CSEB424</td><td>Software Testing</td><td>02A</td><td>N/A</td><td>20/12/17 16:30:00 - 18:30:00</td><td>N/A</td></tr><tr class=\"LINE1\"><td>3.</td><td>CSEB453</td><td>Software Quality</td><td>02</td><td>N/A</td><td>21/12/17 13:30:00 - 15:30:00</td><td>N/A</td></tr><tr class=\"LINE2\"><td>4.</td><td>CSNB544</td><td>Mobile Application Development</td><td>03B</td><td>N/A</td><td>24/12/17 10:00:00 - 12:00:00</td><td>N/A</td></tr></tbody></table><br><br><table><tbody><tr class=\"LINE2\"><td><b>Notes:</b></td></tr></tbody></table></body></html>");
        Document doc = Jsoup.parse(html);
        Element table = doc.getElementsByTag("table").first();
        Element tbody = table.getElementsByTag("tbody").first();
        Elements trs = tbody.getElementsByTag("tr");
        group = 0;
        for (Element tr: trs) {
            String datetime;
            String date, timestart, timeend;
            String name, code, section, seat, loc;
            int startHrs, startMins, endHrs, endMins;
            int start, end;
            Elements td = tr.getElementsByTag("td");
            name = td.get(2).text();
            code = td.get(1).text();
            section = td.get(3).text();
            loc = td.get(4).text();
            seat = td.get(6).text();
            datetime = td.get(5).text();
            if (!datetime.equals("N/A")) {
                date = datetime.split("\\s+")[0];
                timestart = datetime.split("\\s+")[1];
                timeend = datetime.split("\\s+")[3];
                startHrs = (Integer.parseInt(timestart.split(":")[0]) - 8) * 2;
                startMins = Integer.parseInt(timestart.split(":")[1]);
                if (startMins == 30) startMins = 1;
                start = startHrs + startMins;

                endHrs = (Integer.parseInt(timeend.split(":")[0]) - 8) * 2;
                endMins = Integer.parseInt(timeend.split(":")[1]);
                if (endMins == 30) endMins = 1;
                end = endHrs + endMins;
            } else {
                start = -1;
                end = -1;
                date = "";
            }
            examinationDataArrayList.add(new ExaminationData(code, name, date, section, seat, loc, start, end, 0));
            myAdaper.notifyDataSetChanged();
        }
    } //kkk
    private class MyAdapter extends ArrayAdapter <ExaminationData>{
        ArrayList<ExaminationData> examinationDataArrayList;
        int resource;
        public MyAdapter(@NonNull Context context, int resource, ArrayList<ExaminationData> examinationDataArrayList) {
            super(context, resource, examinationDataArrayList);
            this.resource = resource;
            this.examinationDataArrayList = examinationDataArrayList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(resource, parent,false);
            TextView code, name, date, section, seat, loc, start, end;
            code = view.findViewById(R.id.examcode);
            name = view.findViewById(R.id.examname);
            date = view.findViewById(R.id.examdate);
            section = view.findViewById(R.id.examsection);
            seat = view.findViewById(R.id.examseat);
            loc = view.findViewById(R.id.examloc);
            start = view.findViewById(R.id.examstart);
            end = view.findViewById(R.id.examend);

            code.setText(examinationDataArrayList.get(position).getCode());
            name.setText(examinationDataArrayList.get(position).getName());
            date.setText("("+examinationDataArrayList.get(position).getDate()+")");
            section.setText(examinationDataArrayList.get(position).getSection());
            seat.setText(examinationDataArrayList.get(position).getSeat());
            loc.setText(examinationDataArrayList.get(position).getLoc());
            start.setText(examinationDataArrayList.get(position).getStart());
            end.setText(examinationDataArrayList.get(position).getEnd());
            return view;
        }
    }
}
