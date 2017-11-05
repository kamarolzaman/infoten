package com.example.kamarol.infoten_v1;

import android.os.AsyncTask;

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

public class AuthenticateNTLM extends AsyncTask<String, Void, String> {
    StringBuffer result = new StringBuffer();
    int i;
    public AuthenticateNTLM(int i){
        this.i = i;
    }
    @Override
    protected String doInBackground(String... cred) {
        String username = cred[0];
        String password = cred[1];
        String url = cred[2];
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // register ntlm auth scheme
            httpclient.getAuthSchemes().register("ntlm",
                    new NTLMSchemeFactory());
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("info.uniten.edu.my", AuthScope.ANY_PORT),
                    new NTCredentials(username, password, "", ""));

            HttpGet request = new HttpGet(url);
            HttpResponse httpResponse = httpclient.execute(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String line = "";
            line = br.readLine();
            //result.append(line);
            System.out.println(line);
            return line;
        } catch (Exception e){}
        return result.toString();
    }


}
