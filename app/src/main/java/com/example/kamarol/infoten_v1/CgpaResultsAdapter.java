package com.example.kamarol.infoten_v1;

import android.content.Context;
import android.support.v7.widget.CardView;
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
        public TextView cgpaTv , scoreResultTv, nameResultTv;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameResultTv = (TextView) itemView.findViewById(R.id.nameResultTv);
            scoreResultTv = (TextView) itemView.findViewById(R.id.scoreResultTv);
            semesterTv =  (TextView) itemView.findViewById(R.id.semesterResultTv);
            academic_yearTv =  (TextView) itemView.findViewById(R.id.academicYearResultTv);
            gpaTv =  (TextView) itemView.findViewById(R.id.gpaResultTv);
            cgpaTv =  (TextView) itemView.findViewById(R.id.cgpaResultTv);
            cv = itemView.findViewById(R.id.cardGpaResult);
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
        SubjectResult resultb, resultf;
        try {
            resultb = resultList.get(position - 1);
        }catch (Exception e){
            System.out.println(e);
            resultb = resultList.get(position);
        }
        SubjectResult result = resultList.get(position);
        try {
            resultf = resultList.get(position + 1);
        }catch (Exception e){
            System.out.println(e);
            resultf = resultList.get(position);
        }
        System.out.println(resultList.get(position).getSubjectCode());
        TextView nameTv = viewHolder.nameResultTv;
        TextView scoreTv = viewHolder.scoreResultTv;
        TextView semesterTv = viewHolder.semesterTv;
        TextView academic_yearTv = viewHolder.academic_yearTv;
        TextView gpaTv = viewHolder.gpaTv;
        TextView cgpaTv = viewHolder.cgpaTv;
        CardView cv = viewHolder.cv;
        System.out.println(resultList.size());
        if (position==0){ //start
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) cv.getLayoutParams();
            layoutParams.setMargins(20, 10, 20, 0);
            cv.requestLayout();
            gpaTv.setVisibility(View.GONE);
            cgpaTv.setVisibility(View.GONE);
            semesterTv.setVisibility(View.VISIBLE);
            academic_yearTv.setVisibility(View.VISIBLE);
        }else if (position==resultList.size()-1){ //bottom
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) cv.getLayoutParams();
            layoutParams.setMargins(20, 0, 20, 10);
            cv.requestLayout();
            semesterTv.setVisibility(View.GONE);
            academic_yearTv.setVisibility(View.GONE);
            gpaTv.setVisibility(View.VISIBLE);
            cgpaTv.setVisibility(View.VISIBLE);
        }else {
            if (group.get(result)-group.get(resultb)>=1){//start
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) cv.getLayoutParams();
                layoutParams.setMargins(20, 10, 20, 0);
                cv.requestLayout();
                gpaTv.setVisibility(View.GONE);
                cgpaTv.setVisibility(View.GONE);
                semesterTv.setVisibility(View.VISIBLE);
                academic_yearTv.setVisibility(View.VISIBLE);
            } else if (group.get(result)-group.get(resultb)==0 && group.get(resultf)-group.get(result)==0){//middle
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) cv.getLayoutParams();
                layoutParams.setMargins(20, 0, 20, 0);
                cv.requestLayout();
                gpaTv.setVisibility(View.GONE);
                cgpaTv.setVisibility(View.GONE);
                semesterTv.setVisibility(View.GONE);
                academic_yearTv.setVisibility(View.GONE);
            } else if (group.get(resultf)-group.get(result)>=1){//bottom
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) cv.getLayoutParams();
                layoutParams.setMargins(20, 0, 20, 10);
                cv.requestLayout();
                semesterTv.setVisibility(View.GONE);
                academic_yearTv.setVisibility(View.GONE);
                gpaTv.setVisibility(View.VISIBLE);
                cgpaTv.setVisibility(View.VISIBLE);
            }
        }

        semesterTv.setText("Semester: " + String.valueOf(group.get(result)+1));
        academic_yearTv.setText("Trimester: " + String.valueOf(result.getSemester()) + " Year: " + String.valueOf(result.getAcademicYear()));
        nameTv.setText(result.getSubjectCode());
        scoreTv.setText(result.getResultInLetter());
        gpaTv.setText("GPA: " + String.format("%.2f",calculateGpa(result.getSemester(),result.getAcademicYear())));
        cgpaTv.setText("CGPA: " + String.format("%.2f",calculateCgpaAt(result.getSemester(),result.getAcademicYear())));

    }


    public int getItemCount() {
        return resultList.size();
    }

    public CgpaResultsAdapter(Context context, List<SubjectResult> resultList) {
        this.resultList = resultList;
        mContext = context;
        int start = resultList.get(0).getAcademicYear();
        for (SubjectResult result: resultList) {
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
    private double calculateGpa(Integer semester, int academicYear) { //using Integer type causes the == comparison to fail for academic year?!
        double totalCreditHour = 0;
        double totalCreditPoints = 0;
        for (SubjectResult subject : resultList) {
            if ((subject.getSemester() == semester) &&(subject.getAcademicYear() == academicYear)) {
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

