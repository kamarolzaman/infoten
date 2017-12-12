package com.example.kamarol.infoten_v1;

import com.example.kamarol.infoten_v1.Tools.Subject;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        // SEM-1 2015/2016
        exams.add(new SubjectResult("CGNB293", "A", 1, "2015/2016")); // no creditHour constructor
        exams.add(new SubjectResult("CSEB122",  "A+",2, 1, "2015/2016"));
        exams.add(new SubjectResult("CSEB134", "A",4, 1, "2015/2016"));
        exams.add(new SubjectResult("CSNB123", "A",3, 1, "2015/2016"));
        exams.add(new SubjectResult("ICTB112", "A+", 2, 1, "2015/2016"));

        // SEM-2 2015/2016
        exams.add(new SubjectResult("ICTB212", "A",  2, "2015/2016")); // no creditHour constructor
        exams.add(new SubjectResult("CISB214",  "A+",2, "2015/2016")); // no creditHour constructor
        exams.add(new SubjectResult("CSEB233", "B+",4, 2, "2015/2016"));
        exams.add(new SubjectResult("CSNB213", "A",3, 2, "2015/2016"));
        exams.add(new SubjectResult("CSEB214", "A+", 2, 2, "2015/2016"));

        // SEM-3 2015/2016
        exams.add(new SubjectResult("CSNB143", "A", 3, 3, "2015/2016"));
        exams.add(new SubjectResult("HCTB113",  "A-",3, 3, "2015/2016"));
        exams.add(new SubjectResult("ENGB213", "A",4, 2, "2015/2016"));

        // SEM-1 2016/2017
        exams.add(new SubjectResult("CSNB224", "A+",  1, "2016/2017")); // no creditHour constructor
        exams.add(new SubjectResult("CSEB324",  "A", 1, "2016/2017")); // no creditHour constructor
        exams.add(new SubjectResult("CSEB274", "A",4, 1, "2016/2017"));
        exams.add(new SubjectResult("CSEB283", "A",3, 1, "2016/2017"));
        exams.add(new SubjectResult("CGNB313", "A", 4, 1, "2016/2017"));

        // SEM-2 2016/2017
        exams.add(new SubjectResult("CSNB234", "A", 4, 2, "2016/2017"));
        exams.add(new SubjectResult("CSEB294",  "A+",4, 2, "2016/2017"));
        exams.add(new SubjectResult("CISB213", "A",4, 2, "2016/2017"));
        exams.add(new SubjectResult("CSEB343", "A-",3, 2, "2016/2017"));
        exams.add(new SubjectResult("CSEB334", "A", 4, 2, "2016/2017"));

        // SEM-3 2016/2017
        exams.add(new SubjectResult("CGNB316", "LU", 4, 3, "2016/2017"));

        // SEM-1 2017/2018
        exams.add(new SubjectResult("CSNB413", "A+", 3, 1, "2017/2018"));
        exams.add(new SubjectResult("CSEB313",  "B+",3, 1, "2017/2018"));
        exams.add(new SubjectResult("CSEB344", "A-",4, 1, "2017/2018"));
        exams.add(new SubjectResult("ICTB312", "A",2, 1, "2017/2018"));
        exams.add(new SubjectResult("CSEB534", "A+", 4, 1, "2017/2018"));
        }

    @Test
    public void calculateGpaSemesterOne() {
        double Gpa = exams.calculateGpa(1, "2017/2018");
        assertEquals(3.79, Gpa, 0.01);
    }

    @Test
    public void calculateGpaSemesterTwo() {
        double Gpa = exams.calculateGpa(2, "2016/2017");
        assertEquals(3.94, Gpa, 0.01);
    }

    @Test
    public void calculateCGPA() {
        double cgpa = exams.calculateCGPA();
        assertEquals(3.92, cgpa, 0.01);
    }

}
