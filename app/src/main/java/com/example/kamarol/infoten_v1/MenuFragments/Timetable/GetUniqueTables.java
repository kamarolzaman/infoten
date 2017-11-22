package com.example.kamarol.infoten_v1.MenuFragments.Timetable;

import android.content.Context;
import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.Subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by musyrif on 22-Nov-17.
 */

public class GetUniqueTables extends AsyncTask<Void, Void, Void> {
    public static ArrayList<Subject> uniqueSubject2;
    String subject;
    LoaderChecker listener;
    public GetUniqueTables(String subject, LoaderChecker listener){
        System.out.println("GETUNIQUE");
        this.subject = subject;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            uniqueSubject2 = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://ec2-18-217-42-15.us-east-2.compute.amazonaws.com:3306/infoten","infoten","infoten123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from unique_subject WHERE unique_subject.CODE = UPPER('"+subject+"') ORDER BY START");
            while(rs.next()) {
                String code=rs.getString(1);
                String section = rs.getString(2);
                int day = rs.getInt(3);
                int start = rs.getInt(4);
                int end = rs.getInt(5);
                int lectrid = rs.getInt(6);
                uniqueSubject2.add(new Subject(start, end-start, day, code+ " test",section,""));
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        System.out.println("GET UNIQUE LOADED");
        listener.onLoad();
    }
}
