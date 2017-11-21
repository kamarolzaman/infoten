package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by musyrif on 13-Nov-17.
 */

public class GetSubject extends AsyncTask<String, String, Void> {
    TextView textView;
    public GetSubject(TextView textView){
        this.textView = textView;
    }
    @Override
    protected Void doInBackground(String... subject) {
        try{
            String key = subject[0];
            String result = new String();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://ec2-18-217-42-15.us-east-2.compute.amazonaws.com:3306/infoten","infoten","infoten123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from unique_subject where CODE = UPPER('"+key+"')");
            publishProgress(result);
            while(rs.next()) {
                result += "\n" + (rs.getString(1) + " Day: " + rs.getInt(3)+ " Start: " + rs.getInt(4)+ " End: " + rs.getInt(5)+ " Duplicates: " + rs.getInt(7));
                publishProgress(result);
            }
            con.close();

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        textView.setText(values[0]);
    }
}
