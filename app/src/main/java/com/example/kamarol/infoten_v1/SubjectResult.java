package com.example.kamarol.infoten_v1;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kamarol on 12/12/2017.
 */

class SubjectResult implements Comparable<SubjectResult>{
    private String subjectCode;
    private String result;
    private double creditHour;
    private Integer semester;
    private Integer academicYear;
    private static HashMap<String, Double> GradeMapping;
    static {
        GradeMapping = new HashMap<String, Double>();
        GradeMapping.put("A+", 4.0);
        GradeMapping.put("A", 4.0);
        GradeMapping.put("A-", 3.67);
        GradeMapping.put("B+", 3.33);
        GradeMapping.put("B", 3.00);
        GradeMapping.put("B-", 2.67);
        GradeMapping.put("C+", 2.33);
        GradeMapping.put("C", 2.00);
        GradeMapping.put("C-", 1.67);
        GradeMapping.put("D+", 1.33);
        GradeMapping.put("D", 1.00);
        GradeMapping.put("E", 0.00);
        GradeMapping.put("LU", 0.00);
    }
    SubjectResult(String subjectCode, String result, Integer semester, Integer academicYear) {
        this(subjectCode, result, creditHourFromSubjectCode(subjectCode), semester, academicYear);

    }
    SubjectResult(String subjectCode, String result, double creditHour, Integer semester, Integer academicYear) {
        this.subjectCode = subjectCode;
        this.result = result;
        this.creditHour = creditHour;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public SubjectResult(SubjectResultRow row) {
    }

    private static double creditHourFromSubjectCode(String subjectCode) {
        Pattern pattern = Pattern.compile("([0-9]{3,})", Pattern.CASE_INSENSITIVE); //Match 3 number or more.
        Matcher matcher = pattern.matcher(subjectCode);
        String parsedCreditHour = "";
        while(matcher.find()) {
            parsedCreditHour = matcher.group(0);
        }
        Double creditHour = Double.parseDouble(parsedCreditHour.substring(parsedCreditHour.length()-1));
        return creditHour;
    }
    public String getSubjectCode() {
        return subjectCode;
    }

    public String getResultInLetter() {
        return result;
    }

    public double getResultInPoints() throws LulusException{
        if (this.result.equals("LU")) {
            throw new LulusException("Lulus can't get in points!");
        }
        return GradeMapping.get(result);

    }

    public double getCreditHour() {

        return creditHour;
    }

    public Integer getSemester() {
        return semester;
    }

    public Integer getAcademicYear() {
        return academicYear;
    }


    @Override
    public int compareTo(SubjectResult s) {
        if (this.getAcademicYear() > s.getAcademicYear()) {
            return 1;
        } else if (this.getAcademicYear() < s.getAcademicYear()) {
            return -1;
        } else {
            if (this.getSemester() > s.getSemester()) {
                return 1;
            } else if (this.getSemester() < s.getSemester()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
