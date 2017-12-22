package com.example.kamarol.infoten_v1;

import com.example.kamarol.infoten_v1.MenuFragments.Examination.ExaminationData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by musyrif on 20-Dec-17.
 */

public class ExaminationTableParseTest {
    @Test
    public void parseTable(){
        ArrayList<ExaminationData> examinationDataArrayList = new ArrayList<>();

        Document doc = Jsoup.parse("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"><link rel=\"stylesheet\" media=\"screen\" type=\"text/css\" href=\"./exam_files/styles.css\"><link rel=\"stylesheet\" media=\"print\" type=\"text/css\" href=\"./exam_files/print.css\"></head><body><h1>Exam List for Semester 2, Academic Year 2017/2018</h1><table cellspacing=\"0\"><thead><tr valign=\"TOP\"><td rowspan=\"2\">No.</td><td colspan=\"2\">Subject</td><td rowspan=\"2\">Section</td><td rowspan=\"2\">Location</td><td rowspan=\"2\">Date &amp; Time</td><td rowspan=\"2\">Seat</td></tr><tr><td>Code</td><td>Description</td></tr></thead><tbody><tr class=\"LINE1\"><td>1.</td><td>CGNB424</td><td>Project 2</td><td>SE</td><td>N/A</td><td>20/12/17 13:30:00 - 15:30:00</td><td>N/A</td></tr><tr class=\"LINE2\"><td>2.</td><td>CSEB424</td><td>Software Testing</td><td>02A</td><td>N/A</td><td>20/12/17 16:30:00 - 18:30:00</td><td>N/A</td></tr><tr class=\"LINE1\"><td>3.</td><td>CSEB453</td><td>Software Quality</td><td>02</td><td>N/A</td><td>21/12/17 13:30:00 - 15:30:00</td><td>N/A</td></tr><tr class=\"LINE2\"><td>4.</td><td>CSNB544</td><td>Mobile Application Development</td><td>03B</td><td>N/A</td><td>24/12/17 10:00:00 - 12:00:00</td><td>N/A</td></tr></tbody></table><br><br><table><tbody><tr class=\"LINE2\"><td><b>Notes:</b></td></tr></tbody></table></body></html>");
        Element table = doc.getElementsByTag("table").first();
        Element tbody = table.getElementsByTag("tbody").first();
        Elements trs = tbody.getElementsByTag("tr");
        int group = 0;
        for (Element tr: trs) {
            String datetime;
            String date, timestart, timeend;
            String name, code, section, seat, loc;
            int startHrs, startMins, endHrs, endMins;
            int start, end;
            Elements td = tr.getElementsByTag("td");
            name = td.get(2).text();
            code = td.get(1).text();
            section = td.get(3).text();
            loc = td.get(4).text();
            seat = td.get(6).text();
            datetime = td.get(5).text();
            if (!datetime.equals("N/A")) {
                date = datetime.split("\\s+")[0];
                timestart = datetime.split("\\s+")[1];
                timeend = datetime.split("\\s+")[3];

                startHrs = (Integer.decode(timestart.split(":")[0]) - 8) * 2;
                startMins = Integer.decode(timestart.split(":")[1]);
                if (startMins == 30) startMins = 1;
                start = startHrs + startMins;

                endHrs = (Integer.decode(timeend.split(":")[0]) - 8) * 2;
                endMins = Integer.decode(timeend.split(":")[1]);
                if (endMins == 30) endMins = 1;
                end = endHrs + endMins;
            }else{
                start = 0;
                end = 0;
                date = "";
            }
            if (examinationDataArrayList.size()!=0) {
                for (int i = 0; i < examinationDataArrayList.size(); i++) {
                    if (examinationDataArrayList.get(i).getDate().equals(date)) {
                        group = examinationDataArrayList.get(i).getGroup();
                    } else {
                        if (i==examinationDataArrayList.size()-1) {
                            group += 1;
                        }
                    }
                }
            }
            examinationDataArrayList.add(new ExaminationData(code, name, date, section, seat, loc, start, end, group));
        }
        for (ExaminationData e:examinationDataArrayList) {
            System.out.println(e.getName());
            System.out.println(e.getDate());
            System.out.println(e.getStart() + " - " + e.getEnd());
            System.out.println(e.getGroup());
        }
    }
}
