package com.example.kamarol.infoten_v1;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kamarol on 12/13/2017.
 */
//        Pattern pattern = Pattern.compile("([0-9]{3,})", Pattern.CASE_INSENSITIVE); //Match 3 number or more.
//                Matcher matcher = pattern.matcher(subjectCode);

//    \b[A-E][-+\b]?
public class AdvisingTableParser {


    private static Pattern pattern = Pattern.compile("([0-9]{3,})", Pattern.CASE_INSENSITIVE);
    private static Pattern innerPattern = Pattern.compile("\\b([A-E][-+]?)");

    AdvisingTableParser() {

    }

    AdvisingTableParser(Element tableHtml) {
        LinkedHashSet<HashMap> RowList = new LinkedHashSet<>();
        HashMap<String, String> InnerList;
        int i = 0;
        boolean shouldAdd = false;
        Elements data = null;
        boolean saving = false;
        Matcher inner;
        Elements rows = tableHtml.getElementsByTag("tr");
        for (Element row: rows) {
            shouldAdd = false;
            InnerList = new HashMap<>();
            i=0;
            saving = false;
            data = row.getElementsByTag("td");
            for (Element td: data) {
                Matcher matcher = pattern.matcher(td.text()); //if the column is XXXX123 etc
                if (matcher.find()) {
                    saving = true; // then save these next rows
                    shouldAdd = true;
                }
                if (saving == true && i<5) { //change to inner method helper // if save is true and has not moved past 5 rows..
                    if (i==0) {
                        InnerList.put("SUBJECT_CODE", td.text());
                    } else if (i==1) {
                        InnerList.put("SUBJECT_NAME", td.text());
                    } else if (i==2) {
                        InnerList.put("YEAR/SEM", td.text());
                    } else if (i==3) {
                        InnerList.put("CREDIT", td.text());
                    } else if (i==4) {
                        inner = innerPattern.matcher(td.text());
                        boolean found = inner.find();
                        if (!found){
                            shouldAdd = false;
                            break;
                        } else {
                            InnerList.put("RESULT", inner.group(1));
                        }
                    }
                    i++;
                }
            }
            if ( shouldAdd == true) { RowList.add(InnerList); }
            InnerList = null;
        }
        System.out.println("//////////////////////");
        for (HashMap<String, String> l: RowList) {
            System.out.printf(l.toString() + "\n");
        }
        System.out.println(RowList.size());
    }

    public boolean hasRow() {
        return false;
    }

}
