package com.example.kamarol.infoten_v1;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamarol.infoten_v1.Functions.AuthenticateNTLM;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment {
    public static String username, password, NAME;
    Button btn;
    Communicator comm;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "unameKey";
    public static final String Password = "pwKey";
    public static final String Name = "nameKey";
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
                username = uname.getText().toString();
                password = pw.getText().toString();

                if(validLogin(username,password)) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Username, username);
                    editor.putString(Password, password);
                    editor.putString(Name, NAME);
                    editor.commit();
                    getDialog().dismiss();//DISMISS THE CURRENT DIALOG
                    comm.showHome();
                }else{
                    Toast.makeText(view.getContext(), "Invalid credentials.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private static boolean validLogin(String u, String p){
        try {
            String html = new AuthenticateNTLM().execute(u,p,"http://info.uniten.edu.my/info/Ticketing.ASP?WCI=ApplyToGraduate").get(10, TimeUnit.SECONDS);
            System.out.println(html);
            Document doc = Jsoup.parse(html);
            Element namel = doc.select("body > table > tbody > tr:nth-child(3) > td:nth-child(2) > b").first();
            String name = namel.text();
            NAME = name;
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
        return false;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);
        // PUT DECLARATION OR WHATEVER HERE

    }

}
