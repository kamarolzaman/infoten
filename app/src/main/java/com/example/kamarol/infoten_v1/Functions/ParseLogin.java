package com.example.kamarol.infoten_v1.Functions;

import android.os.AsyncTask;

import com.example.kamarol.infoten_v1.LoginCheker;
import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by musyrif on 05-Nov-17.
 */

public class ParseLogin extends AsyncTask<Void, Void, String> {
    private LoginCheker listener;
    StringBuffer result = new StringBuffer();
    StringBuffer result2 = new StringBuffer();
    String html, html2;
    String username, password;
    public ParseLogin(LoginCheker listener, String username, String password){
        this.listener = listener;
        this.username = username;
        this.password = password;
    }
    @Override
    protected String doInBackground(Void... cred) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        DefaultHttpClient httpclient2 = new DefaultHttpClient();
        try {
            // register ntlm auth scheme
            httpclient.getAuthSchemes().register("ntlm", new NTLMSchemeFactory());
            httpclient.getCredentialsProvider().setCredentials(new AuthScope("info.uniten.edu.my", AuthScope.ANY_PORT), new NTCredentials(username, password, "", ""));

            HttpGet request = new HttpGet("http://info.uniten.edu.my/info/Ticketing.ASP?WCI=Biodata");
            HttpResponse httpResponse = httpclient.execute(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            while ((html = br.readLine()) != null) {
                result.append(html);
            }
            HttpGet request2 = new HttpGet("http://info.uniten.edu.my/info/Ticketing.ASP?WCI=ApplyToGraduate");
            HttpResponse httpResponse2 = httpclient.execute(request2);

            BufferedReader br2 = new BufferedReader(new InputStreamReader(httpResponse2.getEntity().getContent()));

            while ((html2 = br2.readLine()) != null) {
                result2.append(html2);
            }
            System.out.println(result2);
            return result.toString();
        } catch (Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        listener.onLogin(result.toString(), result2.toString());
    }
}
