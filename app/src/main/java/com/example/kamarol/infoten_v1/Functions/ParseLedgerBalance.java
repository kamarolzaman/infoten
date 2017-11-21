package com.example.kamarol.infoten_v1.Functions;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by musyrif on 21-Nov-17.
 */

public class ParseLedgerBalance extends AsyncTask <Void, Void, Void>{
    String balance;
    TextView balanceText, status;
    String username, password, url, html;
    StringBuffer result = new StringBuffer();
    boolean isBlocked;

    public ParseLedgerBalance(String username, String password, String url, TextView balanceText, TextView status){
        System.out.println("LEDGER");
        this.username = username;
        this.password = password;
        this.url = url;
        this.balanceText = balanceText;
        this.status = status;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        System.out.println("Executed");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            // register ntlm auth scheme
            httpclient.getAuthSchemes().register("ntlm",
                    new NTLMSchemeFactory());
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("info.uniten.edu.my", AuthScope.ANY_PORT),
                    new NTCredentials(username, password, "", ""));

            HttpGet request = new HttpGet("http://info.uniten.edu.my/info/Ticketing.ASP?WCI=LedgerBalance");
            HttpResponse httpResponse = httpclient.execute(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            //html = br.readLine();

            while ((html = br.readLine()) != null) {
                result.append(html);
            }
        } catch (Exception e){return null;}
        Document selDoc = Jsoup.parse(result.toString());
        System.out.println(selDoc);
        Elements tfoot = selDoc.getElementsByTag("tfoot");
        System.out.println(tfoot);

        Elements tr = tfoot.get(1).getElementsByTag("tr");
        Elements td = tr.get(0).getElementsByTag("td");
        Element result = td.get(6);
        balance = result.text();

        isBlocked=false;
        if(selDoc.select("body > font").attr("color").equals("RED")){
            isBlocked=true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void s) {
        balanceText.setText("RM " + balance);
        if(isBlocked){
            status.setText("You are blocked because of Finance Debt.");
            status.setTextColor(Color.RED);
        } else {
            status.setText("You are not blocked.");
            status.setTextColor(Color.GREEN);
        }
    }
}
