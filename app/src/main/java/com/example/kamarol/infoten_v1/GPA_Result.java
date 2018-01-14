package com.example.kamarol.infoten_v1;

/**
 * Created by kamarol on 1/14/2018.
 */
class GPA_Result {
    private String semester;
    private String academic_year;
    private String gpa;
    private String cgpa;

    public GPA_Result(String semester, String academic_year, String gpa, String cgpa){
        this.semester = semester;
        this.academic_year = academic_year;
        this.gpa = gpa;
        this.cgpa = cgpa;
    }

    public String getSemester() {
        return semester;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public String getGpa() {
        return gpa;
    }

    public String getCgpa() {
        return cgpa;
    }
}
