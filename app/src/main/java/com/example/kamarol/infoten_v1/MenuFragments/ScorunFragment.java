package com.example.kamarol.infoten_v1.MenuFragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamarol.infoten_v1.Functions.ParseScorun;
import com.example.kamarol.infoten_v1.LoaderChecker;
import com.example.kamarol.infoten_v1.LoginFragment;
import com.example.kamarol.infoten_v1.Tools.NTLMSchemeFactory;
import com.example.kamarol.infoten_v1.R;

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
 * A simple {@link Fragment} subclass.
 */
public class ScorunFragment extends Fragment implements LoaderChecker {
    TextView scorun, arts, comm, leader, spirit, sports;
    View view;

    public ScorunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scorun, container, false);
        scorun = view.findViewById(R.id.scorun);
        arts = view.findViewById(R.id.arts);
        comm = view.findViewById(R.id.comm);
        leader = view.findViewById(R.id.leader);
        spirit = view.findViewById(R.id.spirit);
        sports = view.findViewById(R.id.sports);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new ParseScorun(LoginFragment.username, LoginFragment.password, ScorunFragment.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onLoad(String html) {
        String scorunSel = "#ctl00_ContentPlaceHolder1_Label1";
        String neededSel = "#ctl00_ContentPlaceHolder1_Label2";
        Document doc = Jsoup.parse(html);
        Elements el = doc.getElementsByTag("tbody");
        String needed = el.select(neededSel).text();
        String scorun = el.select(scorunSel).text();
        String arts = el.get(14).children().last().children().last().text();
        String comm = el.get(15).children().last().children().last().text();
        String leader = el.get(16).children().last().children().last().text();
        String spirit = el.get(17).children().last().children().last().text();
        String sports = el.get(18).children().last().children().last().text();
        System.out.println(scorun+"/"+needed);
        System.out.println(arts.split("//")[0]);
        System.out.println(comm);
        System.out.println(leader);
        System.out.println(spirit);
        System.out.println(sports);

        this.scorun.setText(scorun+"/"+needed);
        this.arts.setText(arts);
        this.comm.setText(comm);
        this.leader.setText(leader);
        this.spirit.setText(spirit);
        this.sports.setText(sports);
    }
}
