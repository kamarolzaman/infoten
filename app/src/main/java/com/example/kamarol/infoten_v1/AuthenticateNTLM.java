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

public class AuthenticateNTLM extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... cred) {
        String username = cred[0];
        String password = cred[1];
        String url = cred[2];
        StringBuffer result = new StringBuffer();
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
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
            br.close();
            return null;
        } catch (Exception e){}
        return null;
    }
}
