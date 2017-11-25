package com.example.kamarol.infoten_v1.Tools;


/**
 * Created by musyrif on 19-Nov-17.
 */

public class SubjectData {
    String name, code, lecturer, loc;
    int start, end, day;
    public SubjectData(String name, String code, String lecturer, String loc, int start, int end){
        this.name = name;
        this.code = code;
        this.lecturer = lecturer;
        this.loc = loc;
        this.start = start;
        this.end = end;
    }
    public SubjectData(String name, String code, String lecturer, String loc, int start, int end, int day){
        this.name = name;
        this.code = code;
        this.lecturer = lecturer;
        this.loc = loc;
        this.start = start;
        this.end = end;
        this.day =day;
    }
    public String getDay(){
        switch (day){
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
        }
        return "";
    }
    public String getName(){
        return name;
    }
    public String getCode(){
        return code;
    }
    public String getLecturer(){
        return lecturer;
    }
    public String getLoc(){
        return loc;
    }
    public String getStart24(){
        String mins = "00";
        if (start%2==1){
            mins="30";
        }
        String hours = String.valueOf(start/2+8);
        String startStr = hours + ":" + mins;
        return startStr;
    }
    public String getEnd24(){
        String mins = "00";
        if (end%2==1){
            mins="30";
        }
        String hours = String.valueOf(end/2+8);
        String endStr = hours + ":" + mins;
        return endStr;
    }
    public String getStart12(){
        String mins = "00";
        if (start%2==1){
            mins="30";
        }
        int hrs = start/2+8;
        String hours, period;
        if (hrs>12){
            hours = String.valueOf(hrs-12);
            period = " PM";
        } else{
            hours = String.valueOf(hrs);
            period = " AM";
        }
        String startStr = hours + ":" + mins + period;
        return startStr;
    }
    public String getEnd12(){
        String mins = "00";
        if (end%2==1){
            mins="30";
        }
        int hrs = end/2+8;
        String hours, period;
        if (hrs>12){
            hours = String.valueOf(hrs-12);
            period = " PM";
        } else{
            hours = String.valueOf(hrs);
            period = " AM";
        }
        String endStr = hours + ":" + mins + period;
        return endStr;
    }
    public float getDuration(){
        return Float.valueOf(String.valueOf((end-start)/2.0));
    }
}
