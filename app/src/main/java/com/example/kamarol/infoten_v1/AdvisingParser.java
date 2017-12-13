package com.example.kamarol.infoten_v1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by kamarol on 12/13/2017.
 */

public class AdvisingParser {


    private Document pageInstance;

    public Document getPageInstance() throws IOException {
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

    public Element getTable() throws IOException{
        Elements table;
        try {
            table = getPageInstance().getElementsByTag("table");
        }
        catch (IOException e) {
            throw e;
            }

        return table.last();
    }
}
