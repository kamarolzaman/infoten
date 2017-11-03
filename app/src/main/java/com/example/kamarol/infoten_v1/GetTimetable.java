package com.example.kamarol.infoten_v1;

import android.os.AsyncTask;
import android.util.Base64;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by musyrif on 03-Nov-17.
 */

public class GetTimetable extends AsyncTask <String, Void, String>{
    ArrayList<String> subjectInfo = new ArrayList<>(10);
    String tableSel = "http://info.uniten.edu.my/info/Ticketing.ASP?WCI=TimeTable";
    Subject subject[] = new Subject[20];

    @Override
    protected String doInBackground(String... credential) {
        String username = credential[0];
        String password = credential[1];
        try{
            Document selDoc = Jsoup.parse(new GetAuthenticatedResponse(tableSel, "", username, password).toString());
            Element link = selDoc.select("a").first();
            String urlStr = "http://info.uniten.edu.my/info/"+link.attr("href");
            System.out.println(link.text());
            Document doc = Jsoup.parse(new GetAuthenticatedResponse(urlStr, "", username, password).toString());

            Elements test = doc.getElementsByTag("tbody");
            Element tableLecturer = test.get(0);
            Elements tr = tableLecturer.getElementsByTag("tr");
            for (Element td: tr) {
                Elements info = td.getElementsByTag("td");
                subjectInfo.add(info.get(1).text());
                subjectInfo.add(info.get(2).text());
                subjectInfo.add(info.get(4).text());
            }
            for (String info: subjectInfo
                    ) {
                System.out.print(info);
                System.out.println();
            }
            Elements tableStudents = doc.select("body > table:nth-child(7) > tbody");
            int l = 0;
            int day = 0;
            for (Element td: tableStudents){//table row, mon to sun
                Elements tdata = td.getElementsByTag("td");
                int startTime = 0;
                for (Element td1:tdata) {//every td in each trow
                    if (td1.text().equals(" ")) {//empty table
                        startTime++;
                    }else{ //table with subjects
                        String word = td1.attr("colspan");
                        if (!word.equals("")) {//SUBJECT
                            int length = Integer.parseInt(word);//get subject duration
                            String section = subjectInfo.get(subjectInfo.indexOf(td1.text().substring(0, td1.text().indexOf(" ")))+1);
                            String lecturer = subjectInfo.get(subjectInfo.indexOf(td1.text().substring(0, td1.text().indexOf(" ")))+2);
                            subject[l] = new Subject(startTime,length,day,td1.text(),section,lecturer);
                            subject[l].toString();//////////PRINT
                            startTime+=length;
                            l++;

                        }
                    }
                }
                day++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
    }
}
