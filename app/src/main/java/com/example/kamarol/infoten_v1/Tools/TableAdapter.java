package com.example.kamarol.infoten_v1.Tools;

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
 * Created by musyrif on 19-Nov-17.
 */

public class TableAdapter extends ArrayAdapter <SubjectData> {
    ArrayList<SubjectData> mylist;
    public TableAdapter(@NonNull Context context, int resource, ArrayList<SubjectData> myList) {
        super(context, resource, myList);
        this.mylist = myList;
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
        height.getLayoutParams().height =  Math.round(v.getResources().getDimension(R.dimen.class_item_height)*mylist.get(position).getDuration());


        startTime.setText(mylist.get(position).getStart24());
        endTime.setText(mylist.get(position).getEnd24());
        name.setText(mylist.get(position).getName());
        lecturer.setText(mylist.get(position).getLecturer());
        loc.setText(mylist.get(position).getLoc());
        return v;
    }
}
