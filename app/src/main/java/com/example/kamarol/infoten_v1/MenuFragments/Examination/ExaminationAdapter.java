package com.example.kamarol.infoten_v1.MenuFragments.Examination;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.R;

import java.util.ArrayList;

/**
 * Created by musyrif on 20-Nov-17.
 */

public class ExaminationAdapter extends ArrayAdapter<ExaminationData> {
    ArrayList<ExaminationData> examinationData;
    public ExaminationAdapter(@NonNull Context context, int resource, ArrayList<ExaminationData> examinationData) {
        super(context, resource, examinationData);
        this.examinationData = examinationData;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_class, parent,false);
        TextView startTime = v.findViewById(R.id.startTime);
        TextView endTime = v.findViewById(R.id.endTime);
        TextView name = v.findViewById(R.id.name);
        TextView lecturer = v.findViewById(R.id.prof);
        TextView loc = v.findViewById(R.id.place);
        LinearLayout height = v.findViewById(R.id.height);
        height.getLayoutParams().height =  Math.round(v.getResources().getDimension(R.dimen.class_item_height)*examinationData.get(position).getDuration());
        startTime.setText(examinationData.get(position).getStart());
        endTime.setText(examinationData.get(position).getEnd());
        name.setText(examinationData.get(position).getName()+ " "
                + examinationData.get(position).getCode()+ " ("
                + examinationData.get(position).getSection()+ ") ");
        lecturer.setText(examinationData.get(position).getSeat());
        loc.setText(examinationData.get(position).getLoc());
        return v;
    }
}
