package com.example.kamarol.infoten_v1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by kamarol on 12/13/2017.
 */
public class ExaminationResultParser {

    private AdvisingParser parser;

    @Before
    public void setUp() {
        parser = new AdvisingParser();
    }

    @Test
    public void getTable() {
        try {
            Element table = parser.getTable();
            System.out.println(table);
        }
        catch (IOException e) {
            fail();
            }
    }
//    @Test
//    public void getTable() {
//        File input = new File("./app/src/test/java/com/example/kamarol/infoten_v1/testPage/Advising.html");
//        try {
//            Document doc = Jsoup.parse(input, "UTF-8", "");
//            System.out.println(doc);
//        } catch (IOException e) {
//            fail("File not found!");
//        }
//    }

//    @Test
//    public void testLinstEquals() {
//        LinkedList Expected = new LinkedList<String>();
//        LinkedList Actual = new LinkedList<String>();
//        Expected.add("lil");
//        Expected.add("lel");
//        Actual.add("lel");
//        Actual.add("lil");
//        // Fails because order must be same to be equals!
//        assertEquals(Expected, Actual);
//    }

//    @Test
//    public void getTableRow(){
//        List row = parser.getNextRow();
//        asserEquals()
//    }

//    @Test
//    public void parseTable() {
//        AdvisingParser advisingparser = new AdvisingParser();
//        TableParser table = advisingparser;
//        ExaminationResult exam = new ExaminationResult();
//        while(table.hasNextRow()) {
//            Map<String, String> row = table.getRow();
//            exam.add(new SubjectResult(row.get("")));
//        }
//
//    }

}