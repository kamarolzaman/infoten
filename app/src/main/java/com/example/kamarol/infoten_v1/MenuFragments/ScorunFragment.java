package com.example.kamarol.infoten_v1.MenuFragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kamarol.infoten_v1.LoginFragment;
import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;
import com.example.kamarol.infoten_v1.R;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScorunFragment extends Fragment {


    public ScorunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scorun, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new GetScorun().execute(LoginFragment.username, LoginFragment.password);
    }

    private class GetScorun extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... cred) {
            String scorun = "";
            String username = cred[0];
            String password = cred[1];
            DefaultHttpClient httpclient = new DefaultHttpClient();


            try {
                // register ntlm auth scheme
                httpclient.getAuthSchemes().register("ntlm",
                        new NTLMSchemeFactory());
                httpclient.getCredentialsProvider().setCredentials(
                        new AuthScope("info.uniten.edu.my", AuthScope.ANY_PORT),
                        new NTCredentials(username, password, "", ""));

                HttpGet request = new HttpGet("http://info.uniten.edu.my/Scorun/ProgressReport.aspx?mode=student");
                HttpResponse httpResponse = httpclient.execute(request);

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        httpResponse.getEntity().getContent()));

                scorun = br.readLine();
                System.out.println(scorun);
                //result.append(line);
            } catch (Exception e){}
            Document selDoc = Jsoup.parse(scorun);
            System.out.println(selDoc);
            Elements link = selDoc.select("#ctl00_ContentPlaceHolder1_Panel1 > span > table > tbody > tr:nth-child(3) > td:nth-child(2)");
            System.out.println(link.text());
            return null;
        }
    }
}
