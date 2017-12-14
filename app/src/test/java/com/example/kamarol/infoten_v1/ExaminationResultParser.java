package com.example.kamarol.infoten_v1;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//    @Test
//    public void getTable() {
//        try {
//            AdvisingTableParser table = parser.getTable();
//            System.out.println(table);
//        }
//        catch (IOException e) {
//            fail();
//            }
//    }

//    @Test
//    public void testRegex(){
//        Pattern innerPattern = Pattern.compile("\\b[A-E][-+]?");
//        Matcher matcher = innerPattern.matcher("A+");
//        assertEquals(true, matcher.matches());
//    }
    @Test
    public void listOfHashMaps() {
        List<HashMap> listOfHashmaps = new LinkedList<>();
        HashMap<String, String> row = new HashMap<>();
        // for each row... move 5 times to the right?
        row.put("SUBJECT_CODE", "CGNB293");
        row.put("SUBJECT_NAME", "Statistics for Computing");
        row.put("YEAR/SEM", "2015/16S1");
        row.put("RESULT", "A");
        listOfHashmaps.add(row);
        row = new HashMap<>();
        row.put("SUBJECT_CODE", "CISB223");
        row.put("SUBJECT_NAME", "Database");
        row.put("YEAR/SEM", "2015/16S1");
        row.put("RESULT", "C");
        listOfHashmaps.add(row);
//        for (HashMap<String, String> aHashMap: listOfHashmaps) {
//            for(String data: aHashMap.keySet()) {
//                System.out.println(data);
//            }
//        }
        for (HashMap<String, String> aHashMap: listOfHashmaps){
            System.out.println(aHashMap.toString());
        }
    }
    @Test
    public void hashMapAdvisingTable() {
        HashMap<String, String> row = new HashMap<>();
        // for each row... move 5 times to the right?
        row.put("SUBJECT_CODE", "CGNB293");
        row.put("SUBJECT_NAME", "Statistics for Computing");
        row.put("YEAR/SEM", "2015/16S1");
        row.put("RESULT", "A");
    }
    @Test
    public void hashMapDuplicate() {
        HashMap<String, String> duplicate = new HashMap<>();
        duplicate.put("CSEB123", "A+");
        duplicate.put("CSEB123", "B-");
        assertEquals(true, duplicate.get("CSEB123").equals("B-"));
    }

    @Test
    public void regexCaptureGroup() {
        Pattern pattern = Pattern.compile("([A-Z])([0-9])");
        Matcher matcher = pattern.matcher("B2");
        matcher.find();
        assertEquals("B", matcher.group(1));
        assertEquals("2", matcher.group(2));
    }
//    @Test
//    public void getRow() {
//        AdvisingTableParser table = null;
//        ExaminationResult exams = new ExaminationResult();
//        try {
//        table = parser.getTable();
//        }
//        catch (IOException e) {
//            fail();
//        }
////        while(table.hasRow()){
////            table.getNextRow();
////        }
//    }
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