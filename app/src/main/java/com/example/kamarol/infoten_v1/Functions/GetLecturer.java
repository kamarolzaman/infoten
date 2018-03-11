package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;
import android.util.Log;

import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.Tools.Lecturer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by musyrif on 24-Nov-17.
 */

public class GetLecturer extends AsyncTask <Void, Void, Void> {
    private static String className = "GetLecturer";
    public static ArrayList<Lecturer> lecturer;
    LoaderChecker listener;
    String key;
    public GetLecturer(LoaderChecker listener, String key){
        this.listener = listener;
        this.key = key;
    }

    @Override
    protected void onPreExecute() {
        lecturer  = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://ec2-18-217-42-15.us-east-2.compute.amazonaws.com:3306/infoten","infoten","infoten123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from lecturer where NAME LIKE '%"+key+"%'");
            if (isCancelled()){
                Log.d(className, "isCancelled: True");
                return null;
            }
            while(rs.next()) {
                lecturer.add(new Lecturer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            System.out.println(lecturer.toString());
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onLoad("");
    }

    @Override
    protected void onCancelled() {
        System.out.println("Get lecturer cancelled");
        super.onCancelled();
    }
}
