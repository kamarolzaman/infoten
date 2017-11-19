package com.example.kamarol.infoten_v1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by musyrif on 03-Nov-17.
 */

public class Subject{
    SimpleDateFormat test = new SimpleDateFormat("HH:mm");
    Calendar t;
    String fullname, name, loc, day, duration, start, end, section, lecturer;
    int startTimeInt, durationInt, dayInt;


    public Subject(ObjectInputStream ois){
        try{
            this.startTimeInt = ois.readInt();
            this.durationInt = ois.readInt();
            this.dayInt = ois.readInt();
            this.name = ois.readUTF();
            this.section = ois.readUTF();
            this.lecturer = ois.readUTF();

            t = Calendar.getInstance();
            t.setTime(test.parse("08:00"));
        }catch(Exception e){
            System.out.println(e);
        }
        t.add(Calendar.MINUTE, 30*startTimeInt);
        start = test.format(t.getTime());
        t.add(Calendar.MINUTE, 30*durationInt);
        end = test.format(t.getTime());

        this.duration = start + " - " + end;
        String[] splited = name.split("\\s+");
        this.name = splited[0];
        this.loc = splited[1];

        if (dayInt==0){
            day="Monday";
        }else if (dayInt==1){
            day="Tuesday";
        }else if (dayInt==2){
            day="Wednesday";
        }else if (dayInt==3){
            day="Thursday";
        }else if (dayInt==4){
            day="Friday";
        }else if (dayInt==5){
            day="Saturday";
        }else {
            day = "Sunday";
        }
    }

    public Subject(int startTime, int duration, int dayInt, String name, String section, String lecturer){

        try{t = Calendar.getInstance();t.setTime(test.parse("08:00"));}catch(Exception e){}
        t.add(Calendar.MINUTE, 30*startTime);
        start = test.format(t.getTime());
        t.add(Calendar.MINUTE, 30*duration);
        end = test.format(t.getTime());

        this.duration = start + " - " + end;
        this.startTimeInt = startTime;
        this.durationInt = duration;
        this.dayInt = dayInt;
        this.fullname = name;
        String[] splited = name.split("\\s+");
        this.name = splited[0];
        this.loc = splited[1];
        this.section = section;
        this.lecturer = lecturer;
        if (dayInt==0){
            day="Monday";
        }else if (dayInt==1){
            day="Tuesday";
        }else if (dayInt==2){
            day="Wednesday";
        }else if (dayInt==3){
            day="Thursday";
        }else if (dayInt==4){
            day="Friday";
        }else if (dayInt==5){
            day="Saturday";
        }else {
            day = "Sunday";
        }
    }
    public String getLoc(){ return loc;}
    public String getName(){
        return name;
    }
    public String getLecturer(){
        return lecturer;
    }
    public String getSection(){
        return section;
    }
    public int getDay(){
        return dayInt;
    }
    public int getDuration(){
        return durationInt;
    }
    public int getStartTime(){
        return startTimeInt;
    }
    public int getEndTime(){
        return startTimeInt+durationInt;
    }

    public String toString(){
        System.out.println("Day = " + day);
        System.out.println("Name = " + name);
        System.out.println("Location = " + loc);
        System.out.println("Duration = " + duration);
        System.out.println("Section = " + section);
        System.out.println("Lecturer = " + lecturer);
        //System.out.println("Start Time = " + startTimeInt);
        System.out.println();
        return "";
    }
    public String getDetails(){
        return dayInt+"\n" + name+" "+loc+"\n"+duration+"\n"+section+"\n"+lecturer;
    }
    public void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeInt(startTimeInt);
        aOutputStream.flush();
        aOutputStream.writeInt(durationInt);
        aOutputStream.flush();
        aOutputStream.writeInt(dayInt);
        aOutputStream.flush();
        aOutputStream.writeUTF(fullname);
        aOutputStream.flush();
        aOutputStream.writeUTF(section);
        aOutputStream.flush();
        aOutputStream.writeUTF(lecturer);
        aOutputStream.flush();
    }
}