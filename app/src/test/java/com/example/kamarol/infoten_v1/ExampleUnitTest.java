package com.example.kamarol.infoten_v1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private ExaminationResullt exams;

    @Before
    public void setUp() {
        exams = new ExaminationResullt();
        // One semester
        exams.add("CSNB413", "A+", "3", "1", "2017/2018");
        exams.add("CSEB313",  "B+","3", "1", "2017/2018");
        exams.add("CSEB344", "A-","4", "1", "2017/2018");
        exams.add("ICTB312", "A","2", "1", "2017/2018");
        exams.add("CSEB534", "A+","4", "1", "2017/2018");

        // Another semester
        exams.add("CSNB234", "A", "4", "2", "2016/2017");
        exams.add("CSEB294", "A+", "4", "2", "2016/2017");
        exams.add("CISB213", "A", "3", "2", "2016/2017");
        exams.add("CSEB343", "A-", "3", "2", "2016/2017");
        }

    @Test
    public void calculateGpaSemesterOne() {
        double Gpa = exams.calculateGpa("1", "2017/2018");
        assertEquals(3.79, Gpa, 0.1);
    }

//    @Test
//    public void calculateGpaSemesterTwo() {
//        double Gpa = exams.calculateGpa("2", "2016/2017");
//        assertEquals(3.94, Gpa, 0.1);
//    }
}
