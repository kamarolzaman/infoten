package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.LoaderChecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by musyrif on 22-Nov-17.
 */

public class GetSection extends AsyncTask <Void, Void, Void> {
    public static ArrayList<String> section = new ArrayList<String>();
    String subject;
    LoaderChecker listener;
    public GetSection(String subject, LoaderChecker listener){
        this.subject = subject;
        this.listener = listener;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            section.clear();
            System.out.println(subject);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://ec2-18-217-42-15.us-east-2.compute.amazonaws.com:3306/infoten","infoten","infoten123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select SECTION from unique_subject where CODE = UPPER('"+subject+"')");
            while(rs.next()) {
                if (!section.contains(rs.getString(1))){
                    section.add(rs.getString(1));
                }
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        System.out.println("LOADED");
        listener.onLoad("");
    }
}
