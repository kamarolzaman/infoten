package com.example.kamarol.infoten_v1.Functions;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kamarol.infoten_v1.MainActivity;
import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;
import com.example.kamarol.infoten_v1.Subject;

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
import java.util.ArrayList;

/**
 * Created by musyrif on 03-Nov-17.
 */
public class GetTimetable extends AsyncTask<String, String, Void> {
    ProgressDialog dialog;
    ArrayList<String> subjectInfo = new ArrayList<>();
    public static Subject subject[] = new Subject[20];
    String tableSel, html, url;
Context context;
    ListView timetableList;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    public GetTimetable(ListView timetableList, ArrayList<String> listItems, ArrayAdapter<String> adapter){
        this.timetableList = timetableList;
        this.listItems = listItems;
        this.adapter = adapter;
    }
    public GetTimetable(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        dialog = new ProgressDialog(context);
        dialog.setTitle("Loading");
        dialog.setCancelable(false);
        //dialog.show();
        //timetableList.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }

    @Override
    protected Void doInBackground(String... cred) {
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

            HttpGet request = new HttpGet("http://info.uniten.edu.my/info/Ticketing.ASP?WCI=TimeTable");
            HttpResponse httpResponse = httpclient.execute(request);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            tableSel = br.readLine();
            //result.append(line);
        } catch (Exception e){}
        Document selDoc = Jsoup.parse(tableSel);
        Element link = selDoc.select("a").first();
        url = "http://info.uniten.edu.my/info/"+link.attr("href");


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

            html = br.readLine();
            //result.append(line);
        } catch (Exception e){}

        //System.out.println(html);
        Document doc = Jsoup.parse(html);
        Elements test = doc.getElementsByTag("tbody");
        Element tableLecturer = test.get(0);
        Elements tr = tableLecturer.getElementsByTag("tr");
        for (Element td: tr) {
            Elements info = td.getElementsByTag("td");
            subjectInfo.add(info.get(1).text());
            subjectInfo.add(info.get(2).text());
            subjectInfo.add(info.get(4).text());
        }
        Element tableStudents = doc.select("body > table:nth-child(7) > tbody").get(0);
        int l = 0;
        int day = 0;
        Elements trows = tableStudents.getElementsByTag("tr");
        int startTime = 0;
        for (Element tdata:trows) {//every td in each trow
            Elements tdatas = tdata.getElementsByTag("td");
            for (Element td1:tdatas) {
                if (td1.text().equals(" ")) {//empty table
                    startTime++;
                } else { //table with subjects
                    String word = td1.attr("colspan");
                    if (!word.equals("")) {//SUBJECT
                        int length = Integer.parseInt(word);//get subject duration
                        String section = subjectInfo.get(subjectInfo.indexOf(td1.text().substring(0, td1.text().indexOf(" "))) + 1);
                        String lecturer = subjectInfo.get(subjectInfo.indexOf(td1.text().substring(0, td1.text().indexOf(" "))) + 2);
                        subject[l] = new Subject(startTime, length, day, td1.text(), section, lecturer);
                        //subject[l].toString();//////////PRINT
                        //publishProgress(subject[l].getDetails());
                        startTime += length;
                        l++;
                    }
                }
            }
            day++;
            startTime = 0;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismiss();
        new SendTable().execute();
        super.onPostExecute(aVoid);
    }
}
