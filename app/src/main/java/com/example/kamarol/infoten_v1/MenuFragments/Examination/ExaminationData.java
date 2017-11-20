package com.example.kamarol.infoten_v1.MenuFragments.Examination;

/**
 * Created by musyrif on 20-Nov-17.
 */

public class ExaminationData {
    String name, code, section, seat, loc;
    int start, end;
    public ExaminationData(String name, String code, String section, String seat, String loc, int start, int end){
        this.name = name;
        this.code = code;
        this.section = section;
        this.seat = seat;
        this.loc = loc;
        this.start = start;
        this.end = end;
    }
    public String getName(){
        return name;
    }
    public String getCode(){
        return code;
    }
    public String getSection(){
        return section;
    }
    public String getSeat(){
        return seat;
    }
    public String getLoc(){
        return loc;
    }
    public String getStart(){
        String mins = "00";
        if (start%2==1){
            mins="30";
        }
        String hours = String.valueOf(start/2+8);
        String startStr = hours + ":" + mins;
        return startStr;
    }
    public String getEnd(){
        String mins = "00";
        if (end%2==1){
            mins="30";
        }
        String hours = String.valueOf(end/2+8);
        String endStr = hours + ":" + mins;
        return endStr;
    }
    public float getDuration(){
        return Float.valueOf(String.valueOf((end-start)/2.0));
    }
}
