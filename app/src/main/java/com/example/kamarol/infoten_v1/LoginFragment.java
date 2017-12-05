package com.example.kamarol.infoten_v1;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamarol.infoten_v1.Functions.ParseLogin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment implements LoginCheker {
    ProgressDialog progressDialog;
    public static String username, password, NAME, advisor, semester;
    Button btn;
    Communicator comm;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "unameKey";
    public static final String Password = "pwKey";
    public static final String Name = "nameKey";
    public static final String Advisor = "advisorKey";
    public static final String Semester = "semesterKey";
    EditText uname,pw;
    SharedPreferences sharedpreferences;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SET THE THE STYLING OF THE FRAGMENT TO NO FRAME
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        setCancelable(true);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        comm.dismissDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //ASSOCIATE LoginFragment TO fragment_login.xml -VVVV-
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn=(Button)view.findViewById(R.id.loginButton);
        uname = view.findViewById(R.id.username);
        pw = view.findViewById(R.id.password);
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        //YOU CAN'T SET ONCLICK ON xml FILE TO RUN A METHOD HERE
        btn.setOnClickListener(new View.OnClickListener() { //INSTEAD YOU EMBED A LISTENER TO THAT BUTTON

            @Override
            public void onClick(View v) {//WHEN THE EMBEDED LISTENER GET AN onClick, IT WILL RUN THIS METHOD
                username = uname.getText().toString().toLowerCase();
                password = pw.getText().toString();
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Loging in..");
                progressDialog.setCancelable(false);
                progressDialog.show();
                new ParseLogin(LoginFragment.this,username,password).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);
        // PUT DECLARATION OR WHATEVER HERE

    }

    @Override
    public void onLogin(String html, String html2) {
        progressDialog.dismiss();
        int valid = 0;
        String name = "", semester = "", advisor = "";
        try {
            Document doc = Jsoup.parse(html);
            name = doc.select("body > form > table > tbody > tr:nth-child(2) > td:nth-child(2)").first().text();
            advisor = doc.select("body > form > table > tbody > tr:nth-child(8) > td:nth-child(2)").first().text();
            Document doc2 = Jsoup.parse(html2);
            System.out.println(html2);
            String sem = doc2.select("body > h1").first().text();
            semester = sem.replace("Time Table for ", "");
            System.out.println(name +advisor+semester);
            valid=1;
        }catch (Exception e){
            e.printStackTrace();
            valid=0;
        }
        if (valid==1){
            this.NAME = name;
            this.advisor = advisor;
            this.semester = semester;
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Username, username);
            editor.putString(Password, password);
            editor.putString(Name, NAME);
            editor.putString(Advisor, advisor);
            editor.putString(Semester, semester);
            editor.commit();
            getDialog().dismiss();//DISMISS THE CURRENT DIALOG
            comm.showHome();
        }else{
            Toast.makeText(getActivity(), "Invalid login", Toast.LENGTH_SHORT).show();
        }

    }
}
