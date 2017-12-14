package com.example.kamarol.infoten_v1;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
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

    AdvisingTableParser(Element tableHtml) {
        List<LinkedList> RowList = new LinkedList<LinkedList>();
        LinkedList<String> InnerList;
        int i = 0;
        boolean shouldAdd = false;
        Elements data = null;
        boolean saving = false;
        Matcher inner;
        Elements rows = tableHtml.getElementsByTag("tr");
        for (Element row: rows) {
            shouldAdd = true;
            InnerList = new LinkedList<>();
            i=0;
            saving = false;
            data = row.getElementsByTag("td");
            for (Element td: data) {
                Matcher matcher = pattern.matcher(td.text());
                if (matcher.find()) {
                    saving = true;
                }
                if (saving == true && i<5) { //change to inner method helpwer
                    if (i==4) {
                        inner = innerPattern.matcher(td.text());
//                        if (td.text().length() == 0 || td.text().length() > 2) {
                        boolean found = inner.find();
                        if (!found){
                            shouldAdd = false;
                            break;
                        } else {
                            InnerList.add(inner.group(1));
                            i++;
                            continue;
                        }
                    }
                    InnerList.add(td.text());
                    i++;
                }
            }
            if ( shouldAdd == true) { RowList.add(InnerList); }
            InnerList = null;
        }
        System.out.println("//////////////////////");
        for (LinkedList<String> l: RowList) {
            for (String lil: l) {
                System.out.println(lil);
            }
        }
        System.out.println(RowList.size());
    }

    public boolean hasRow() {
        return false;
    }

//    public HashMap<String, Object> getNextRow() {
//        return Row
//    }
}
