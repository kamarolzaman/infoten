package com.example.kamarol.infoten_v1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kamarol on 1/14/2018.
 */

public class CgpaResultsAdapter extends RecyclerView.Adapter<CgpaResultsAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView semesterTv;
        public TextView academic_yearTv;
        public TextView gpaTv;
        public TextView cgpaTv;

        public ViewHolder(View itemView) {
            super(itemView);
            semesterTv =  (TextView) itemView.findViewById(R.id.semesterResultTv);
            academic_yearTv =  (TextView) itemView.findViewById(R.id.academicYearResultTv);
            gpaTv =  (TextView) itemView.findViewById(R.id.gpaResultTv);
            cgpaTv =  (TextView) itemView.findViewById(R.id.cgpaResultTv);
        }

    }

    private List<SubjectResult> resultList;
    private Context mContext;
    private HashMap<SubjectResult, Integer> group = new HashMap<>();

    @Override
    public CgpaResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View examResultCard = inflater.inflate(R.layout.exam_result_card, parent, false);
        ViewHolder viewholder = new ViewHolder(examResultCard);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(CgpaResultsAdapter.ViewHolder viewHolder, int position) {
        SubjectResult result = resultList.get(position);
        System.out.println(resultList.get(position).getSubjectCode());
        TextView semesterTv = viewHolder.semesterTv;
        TextView academic_yearTv = viewHolder.academic_yearTv;
        TextView gpaTv = viewHolder.gpaTv;
        TextView cgpaTv = viewHolder.cgpaTv;
        
        semesterTv.setText(String.valueOf(result.getSemester()) + " " + String.valueOf(result.getAcademicYear()));
        academic_yearTv.setText(result.getSubjectCode());
        gpaTv.setText(Double.toString(calculateCgpaAt(result.getSemester(),result.getAcademicYear())));
        cgpaTv.setText("Group: " + String.valueOf(group.get(result)));

    }


    public int getItemCount() {
        return resultList.size();
    }

    public CgpaResultsAdapter(Context context, List<SubjectResult> resultList) {
        this.resultList = resultList;
        mContext = context;
        int x = 0;
        for (SubjectResult result: resultList) {
            int start = result.getAcademicYear();
            for (int i = start; i < start+6; i++) {
                for (int j = 1; j <= 3; j++) {
                    if (result.getSemester()==1 && result.getAcademicYear()==start) group.put(result,0);
                    else if (result.getSemester()==2 && result.getAcademicYear()==start) group.put(result,1);
                    else if (result.getSemester()==3 && result.getAcademicYear()==start) group.put(result,2);

                    else if (result.getSemester()==1 && result.getAcademicYear()==start+1) group.put(result,3);
                    else if (result.getSemester()==2 && result.getAcademicYear()==start+1) group.put(result,4);
                    else if (result.getSemester()==3 && result.getAcademicYear()==start+1) group.put(result,5);

                    else if (result.getSemester()==1 && result.getAcademicYear()==start+2) group.put(result,6);
                    else if (result.getSemester()==2 && result.getAcademicYear()==start+2) group.put(result,7);
                    else if (result.getSemester()==3 && result.getAcademicYear()==start+2) group.put(result,8);

                    else if (result.getSemester()==1 && result.getAcademicYear()==start+3) group.put(result,9);
                    else if (result.getSemester()==2 && result.getAcademicYear()==start+3) group.put(result,10);
                    else if (result.getSemester()==3 && result.getAcademicYear()==start+3) group.put(result,11);

                    else if (result.getSemester()==1 && result.getAcademicYear()==start+4) group.put(result,12);
                    else if (result.getSemester()==2 && result.getAcademicYear()==start+4) group.put(result,13);
                    else if (result.getSemester()==3 && result.getAcademicYear()==start+4) group.put(result,14);

                    else if (result.getSemester()==1 && result.getAcademicYear()==start+5) group.put(result,15);
                    else if (result.getSemester()==2 && result.getAcademicYear()==start+5) group.put(result,16);
                    else if (result.getSemester()==3 && result.getAcademicYear()==start+5) group.put(result,17);
                }
            }
        }
    }

    private Context getContext() {
        return mContext;
    }
    private double calculateCgpaAt(int semester, int year) {
        double totalCreditHour = 0;
        double totalCreditPoints = 0;
        for (SubjectResult subject : resultList) {
            if ((subject.getSemester() <= semester) &&(subject.getAcademicYear() <= year)) {
                try {
                    totalCreditPoints += subject.getResultInPoints() * subject.getCreditHour();
                    totalCreditHour += subject.getCreditHour();
                } catch (LulusException E) {
                    E.printStackTrace();
                }
            }
        }
        return (totalCreditPoints / totalCreditHour);
    }
}

