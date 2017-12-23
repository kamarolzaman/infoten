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

    // Actual test
    @Test
    public void getRow() {
        AdvisingTableParser table = null;
        ExaminationResult exams = new ExaminationResult();
        try {
        parser.getResults();
        }
        catch (IOException e) {
            fail();
        }
    }

}