package com.example.kamarol.infoten_v1;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExaminationTest {

    private ExaminationResult exams;

    @Before
    public void setUp() {
        exams = new ExaminationResult();
        // SEM-1 2015/2016
        exams.add(new SubjectResult("CGNB293", "A", 1, 2015)); // no creditHour constructor
        exams.add(new SubjectResult("CSEB122",  "A+",2, 1, 2015));
        exams.add(new SubjectResult("CSEB134", "A",4, 1, 2015));
        exams.add(new SubjectResult("CSNB123", "A",3, 1,2015));
        exams.add(new SubjectResult("ICTB112", "A+", 2, 1,2015));

        // SEM-2 2015/2016
        exams.add(new SubjectResult("ICSB212", "A",  2,2015)); // no creditHour constructor
        exams.add(new SubjectResult("CISB214",  "A+",2,2015)); // no creditHour constructor
        exams.add(new SubjectResult("CSEB233", "B+",3, 2,2015));
        exams.add(new SubjectResult("CSNB213", "A",3, 2,2015));
        exams.add(new SubjectResult("CSEB214", "A+", 4, 2,2015));

        // SEM-3 2015/2016
        exams.add(new SubjectResult("CSNB143", "A", 3, 3,2015));
        exams.add(new SubjectResult("HCTB113",  "A-",3, 3,2015));
        exams.add(new SubjectResult("ENGB213", "A",4, 3,2015));

        // SEM-1 2016/2017
        exams.add(new SubjectResult("CSNB224", "A+",  1,2016)); // no creditHour constructor
        exams.add(new SubjectResult("CSEB324",  "A", 1,2016)); // no creditHour constructor
        exams.add(new SubjectResult("CSEB274", "A",4, 1,2016));
        exams.add(new SubjectResult("CSEB283", "A",3, 1,2016));
        exams.add(new SubjectResult("CGNB313", "A", 3, 1,2016));

        // SEM-2 2016/2017
        exams.add(new SubjectResult("CSNB234", "A", 4, 2,2016));
        exams.add(new SubjectResult("CSEB294",  "A+",4, 2,2016));
        exams.add(new SubjectResult("CISB213", "A",3, 2,2016));
        exams.add(new SubjectResult("CSEB343", "A-",3, 2,2016));
        exams.add(new SubjectResult("CSEB334", "A", 4, 2,2016));

        // SEM-3 2016/2017
        exams.add(new SubjectResult("CGNB316", "LU", 6, 3,2016));

        // SEM-1 2017/2018
        exams.add(new SubjectResult("CSNB413", "A+", 3, 1,2017));
        exams.add(new SubjectResult("CSEB313",  "B+",3, 1,2017));
        exams.add(new SubjectResult("CSEB344", "A-",4, 1,2017));
        exams.add(new SubjectResult("ICTB312", "A",2, 1,2017));
        exams.add(new SubjectResult("CSEB534", "A+", 4, 1,2017));
        }

    @Test
    public void calculateGpaSemesterOne() {
        double gpa = exams.calculateGpa(1,2017);
        assertEquals(3.79, gpa, 0.01);
    }

    @Test
    public void calculateGpaSemesterTwo() {
        double gpa = exams.calculateGpa(2,2016);
        assertEquals(3.94, gpa, 0.01);
    }

    @Test
    public void calculateCGPA() {
        double cgpa = exams.calculateCGPA();
        assertEquals(3.92, cgpa, 0.01);
    }

    @Test
    public void calculateCgpaAtSemesterTwo2015(){
        double cgpa = exams.calculateCgpaAt(2, 2015);
        assertEquals(3.93, cgpa, 0.01);
    }

    @Test
    public void calculateCgpaAtSemesterTwo2016(){
        double cgpa = exams.calculateCgpaAt(2, 2016);
        assertEquals(3.95, cgpa, 0.01);
    }

    @Test
    public void calculateCgpaAtSemesterThree2015(){
        double cgpa = exams.calculateCgpaAt(3, 2015);
        assertEquals(3.92, cgpa, 0.01);
    }

    @Test
    public void UseParseInstead() {
//        AdvisingParser parser = new AdvisingParser();
//        try {
//            AdvisingTableParser tableParser = parser.getTable();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ExaminationResult examinationResult = new ExaminationResult();
        AdvisingParser advisingParser = new AdvisingParser();
        try {
            for (HashMap<String, String> parserData : advisingParser.getResults()){
                examinationResult.add(new SubjectResult(parserData.get("SUBJECT_CODE"), parserData.get("RESULT"), Integer.parseInt(parserData.get("SEMESTER")), Integer.parseInt(parserData.get("YEAR"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double cgpa = examinationResult.calculateCGPA();
        assertEquals("CGPA is wrong!", 3.92, cgpa, 0.01);
    }


}

class ExaminationResult_AdvisingParser_Integration {


}