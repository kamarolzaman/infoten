package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.LoginFragment;
import com.example.kamarol.infoten_v1.MainActivity;
import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by musyrif on 14-Jan-18.
 */

public class ParseAdvising extends AsyncTask <Void, Void, Void> {
    LoaderChecker listener;
    String html;
    StringBuffer result = new StringBuffer();
    public ParseAdvising (LoaderChecker listener){
        this.listener = listener;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getAuthSchemes().register("ntlm",
                    new NTLMSchemeFactory());
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(MainActivity.url2 , AuthScope.ANY_PORT),
                    new NTCredentials(LoginFragment.username, LoginFragment.password, "", ""));

            HttpGet request = new HttpGet(MainActivity.url + "/Ticketing.ASP?WCI=Advising");
            HttpResponse httpResponse = httpclient.execute(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            while ((html = br.readLine()) != null) {
                result.append(html);
            }

        } catch (Exception e){return null;}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onLoad(result.toString());
    }
}
