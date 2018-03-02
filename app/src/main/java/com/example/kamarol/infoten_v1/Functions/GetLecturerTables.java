package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.Tools.Subject;
import com.example.kamarol.infoten_v1.Tools.SubjectData;
import com.example.kamarol.infoten_v1.Tools.TableLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by musyrif on 01-Dec-17.
 */

public class GetLecturerTables extends AsyncTask<Void, Void, Void> {
    public static ArrayList<Subject> lecturerTables;
    String lecturer;
    TableLoader listener;
    public GetLecturerTables(String lecturer, TableLoader listener){
        this.lecturer = lecturer;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            lecturerTables = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://ec2-18-217-42-15.us-east-2.compute.amazonaws.com:3306/infoten","infoten","infoten123");
            Statement stmt = con.createStatement();
            String sql = "select unique_subject.CODE, unique_subject.LOCATION, unique_subject.SECTION, unique_subject.DAY, unique_subject.START, unique_subject.END, lecturer.NAME from unique_subject INNER JOIN lecturer ON unique_subject.LECTURER_ID = lecturer.ID WHERE lecturer.NAME = '"+lecturer+"' ORDER BY DAY, START";
            //System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String code=rs.getString(1);
                String loc =rs.getString(2);
                String section = rs.getString(3);
                int day = rs.getInt(4);
                int start = rs.getInt(5);
                int end = rs.getInt(6);
                String lectrid = rs.getString(7);
                lecturerTables.add(new Subject(start, end-start, day, code+" "+loc, section, lectrid));
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onTableLoad("");
    }


    @Override
    protected void onCancelled() {
        System.out.println("Get lecturer tables cancelled");
        super.onCancelled();
    }
}
