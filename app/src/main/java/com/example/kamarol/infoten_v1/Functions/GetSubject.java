package com.example.kamarol.infoten_v1.Functions;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.MenuFragments.SearchSubjectFragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by musyrif on 13-Nov-17.
 */

public class GetSubject extends AsyncTask<Void, String, Void> {
    TextView textView;
    String key;
    ProgressDialog nDialog;
    Context context;
    public GetSubject(TextView textView, Context context, String key){
        this.textView = textView;
        this.context = context;
        this.key = key;
    }

    @Override
    protected void onPreExecute() {
        nDialog = new ProgressDialog(context);
        nDialog.setMessage("Geting data..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();
    }

    @Override
    protected Void doInBackground(Void... subject) {
        try{
            System.out.println(key);
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
        if(textView!=null) {
            textView.setText(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        nDialog.dismiss();
    }
}
