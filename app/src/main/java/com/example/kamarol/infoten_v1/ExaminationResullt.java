package com.example.kamarol.infoten_v1;

import com.example.kamarol.infoten_v1.Tools.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamarol on 12/11/2017.
 */

class ExaminationResullt {

    private List<SubjectResult> subjectResultList;

    ExaminationResullt() {
        subjectResultList = new ArrayList<SubjectResult>();
    }

    public void add(SubjectResult subjectresult) {
        subjectResultList.add(subjectresult);
    }

    public double calculateGpa(Integer semester, String academicYear) {
        double totalCreditHour = 0;
        double totalCreditPoints = 0;
        for (SubjectResult subject : subjectResultList) {
            if (subject.getSemester().equals(semester) && subject.getAcademicYear().equals(academicYear)) {
                try {
                    totalCreditPoints += subject.getResultInPoints() * subject.getCreditHour();
                    totalCreditHour += subject.getCreditHour();
                } catch (LulusException E) {
                    E.printStackTrace();
                }
            }
        }
        return (totalCreditPoints / totalCreditHour);
    }

    // todo: refactor duplicate code -- gpa and cgpa uses the same algorithm
    public double calculateCGPA() {
        double totalCreditHour = 0;
        double totalCreditPoints = 0;
        for (SubjectResult subject : subjectResultList) {
            try {
                totalCreditPoints += subject.getResultInPoints() * subject.getCreditHour();
                totalCreditHour += subject.getCreditHour();
            } catch (LulusException E) {
                System.out.println(E.getMessage());
            }
        }
        System.out.println(totalCreditPoints/totalCreditHour);
        return (totalCreditPoints / totalCreditHour);
    }
}
