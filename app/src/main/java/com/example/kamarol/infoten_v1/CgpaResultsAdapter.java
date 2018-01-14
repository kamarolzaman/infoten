package com.example.kamarol.infoten_v1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private List<SubjectResult> mGpa_result;
    private Context mContext;


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
        SubjectResult gpa_result = mGpa_result.get(position);
        TextView semesterTv = viewHolder.semesterTv;
        TextView academic_yearTv = viewHolder.academic_yearTv;
        TextView gpaTv = viewHolder.gpaTv;
        TextView cgpaTv = viewHolder.cgpaTv;

        semesterTv.setText(gpa_result.getSemester());
        academic_yearTv.setText(gpa_result.getAcademicYear());
        gpaTv.setText("GPA");
        cgpaTv.setText("CGPA");

    }


    public int getItemCount() {
        return mGpa_result.size();
    }

    public CgpaResultsAdapter(Context context, List<SubjectResult> gpa_cards) {
        mGpa_result = gpa_cards;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }
}

