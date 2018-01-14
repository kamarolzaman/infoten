package com.example.kamarol.infoten_v1;

import com.example.kamarol.infoten_v1.Tools.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kamarol on 12/11/2017.
 */

class ExaminationResult { //need to implement iterable to work!

    private List<SubjectResult> subjectResultList;

    ExaminationResult() {
        subjectResultList = new ArrayList<SubjectResult>();
    }

    public List<SubjectResult> getSubjectResults() {
        return this.subjectResultList;
    }
    public void add(SubjectResult subjectresult) {
        subjectResultList.add(subjectresult);
    }

    public double calculateGpa(Integer semester, int academicYear) { //using Integer type causes the == comparison to fail for academic year?!
        double totalCreditHour = 0;
        double totalCreditPoints = 0;
        for (SubjectResult subject : subjectResultList) {
            if ((subject.getSemester() == semester) &&(subject.getAcademicYear() == academicYear)) {
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

    public double calculateCgpaAt(int semester, int year) {
        double totalCreditHour = 0;
        double totalCreditPoints = 0;
        for (SubjectResult subject : subjectResultList) {
            if ((subject.getSemester() <= semester) &&(subject.getAcademicYear() <= year)) {
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

}
