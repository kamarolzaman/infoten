package com.example.kamarol.infoten_v1;

import android.content.Loader;
import android.os.AsyncTask;
import android.widget.Adapter;

import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kamarol on 12/13/2017.
 */

public class AdvisingParser {

    private LoaderChecker listener;
    private Document pageInstance;

    public Document getPageInstance() throws IOException  {
//        String html;
//        StringBuffer result = new StringBuffer();
//
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        try {
//            httpclient.getAuthSchemes().register("ntlm",
//                    new NTLMSchemeFactory());
//            httpclient.getCredentialsProvider().setCredentials(
//                    new AuthScope("info.uniten.edu.my", AuthScope.ANY_PORT),
//                    new NTCredentials(LoginFragment.username, LoginFragment.password, "", ""));
//
//            HttpGet request = new HttpGet("http://info.uniten.edu.my/info/Ticketing.ASP?WCI=Exam");
//            HttpResponse httpResponse = httpclient.execute(request);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    httpResponse.getEntity().getContent()));
//
//            while ((html = br.readLine()) != null) {
//                result.append(html);
//            }
//        } catch (Exception e){return null;}
//        return Jsoup.parse(result.toString());
        if (pageInstance != null) {
            return pageInstance;
        } else {
            // todo: Change test path to use actual relative path. this string might break if the project is renamed.
            // todo: Change getInstance to just initialize in a constructor perhaps?
            File input = new File("./app/src/test/java/com/example/kamarol/infoten_v1/testPage/Advising.html");
            try {
                pageInstance = Jsoup.parse(input, "UTF-8", "");
            } catch (IOException e) {
                throw new IOException("file not found!");
            }
            return pageInstance;
        }
    }


    public AdvisingTableParser getTable() throws IOException {
        Elements tableHtml;
        try {
            tableHtml = getPageInstance().getElementsByTag("table");
        }
        catch (IOException e) {
            throw e;
            }
        AdvisingTableParser table = new AdvisingTableParser(tableHtml.last());

        // should return html instead!!
        // tukar jsoup ke linkedhashmap, AdvisingTableparser
        return table;
    }


}
