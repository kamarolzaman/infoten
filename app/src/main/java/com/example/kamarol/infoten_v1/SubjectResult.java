package com.example.kamarol.infoten_v1;

import java.util.HashMap;

/**
 * Created by kamarol on 12/12/2017.
 */

class SubjectResult {
    private String subjectCode;
    private String result;
    private double creditHour;
    private Integer semester;
    private String academicYear;
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
    }

    SubjectResult(String subjectCode, String result, double creditHour, Integer semester, String academicYear) {
        this.subjectCode = subjectCode;
        this.result = result;
        this.creditHour = creditHour;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getResultInLetter() {
        return result;
    }

    public double getResultInPoints(){
        return GradeMapping.get(result);

    }

    public double getCreditHour() {
        return creditHour;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getAcademicYear() {
        return academicYear;
    }


}
