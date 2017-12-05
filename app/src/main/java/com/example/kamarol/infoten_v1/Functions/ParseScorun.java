package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by musyrif on 21-Nov-17.
 */

public class ParseScorun extends AsyncTask <Void, Void, Void> {
    StringBuffer result = new StringBuffer();
    TextView textView;
    String username, password, url, html;
    LoaderChecker listener;

    public ParseScorun(String username, String password, LoaderChecker listener){
        this.username = username;
        this.password = password;
        this.listener = listener;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getAuthSchemes().register("ntlm",
                    new NTLMSchemeFactory());
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("info.uniten.edu.my", AuthScope.ANY_PORT),
                    new NTCredentials(username, password, "", ""));

            HttpGet request = new HttpGet("http://info.uniten.edu.my/scorun/ProgressReport.aspx?mode=student");
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
