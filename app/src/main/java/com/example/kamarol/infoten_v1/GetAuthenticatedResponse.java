package com.example.kamarol.infoten_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Created by musyrif on 03-Nov-17.
 */

public class GetAuthenticatedResponse {
    private StringBuilder response = new StringBuilder();
    public GetAuthenticatedResponse(final String urlStr, final String domain, final String userName, final String password) throws IOException {

        Authenticator.setDefault(new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(domain + "\\" + userName, password.toCharArray());
            }
        });
        System.out.println(userName+password);
        URL urlRequest = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");

        System.out.println(conn.getResponseCode());

        InputStream stream = conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String str = "";
        while ((str = in.readLine()) != null) {
            response.append(str);
        }
        in.close();
    }
    public String toString(){
        return response.toString();
    }
}
