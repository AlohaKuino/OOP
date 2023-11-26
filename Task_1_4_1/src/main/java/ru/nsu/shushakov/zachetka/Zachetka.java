package ru.nsu.shushakov.zachetka;

import java.util.ArrayList;
import java.util.List;

/**
 * main class.
 */
public class Zachetka {
    public String nameOfStudent;
    public int numberOfTheGroup;

    public int endedSemester;
    public List<List<SubjectAndMark>> allSemesters;
    public SubjectAndMark diploma;

    /**
     * @param nameOfStudent                 students name.
     * @param numberOfTheGroup              group number.
     * @param endedSemester                 semester that is ended.
     * @param streamOfSuspiciousInformation array of subjects and marks.
     * @throws SemesterException if semester > 8 then it will be thrown.
     *
     * constructor.
     */
    public Zachetka(String nameOfStudent, int numberOfTheGroup, int endedSemester,
                    ArrayList<SubjectAndMark> streamOfSuspiciousInformation)
            throws SemesterException {
        this.nameOfStudent = nameOfStudent;
        this.endedSemester = endedSemester;
        if (this.endedSemester > 8) {
            throw new SemesterException("\n\n are you this old? \n");
        }
        this.allSemesters = new ArrayList<>();
        setAllSemesters(streamOfSuspiciousInformation);
    }

    /**
     * @param streamOfSuspiciousInformation array of subjects and marks.
     *
     * creating an array of arrays without nullPointerException.
     */
    public void setAllSemesters(List<SubjectAndMark> streamOfSuspiciousInformation) {
        for (int i = 0; i <= endedSemester; i++) {
            ArrayList<SubjectAndMark> semester = new ArrayList<>();
            this.allSemesters.add(semester);
        }
        for (SubjectAndMark subjectAndMark : streamOfSuspiciousInformation) {
            if (subjectAndMark.isDiploma) {
                this.diploma = subjectAndMark;
                continue;
            }
            this.allSemesters.get(subjectAndMark.semester).add(subjectAndMark);
        }
    }

    /**
     * @return average ball from the beninging to the ended semester.
     *
     * using iTriedToStreamButMyHeadWasUnderWater.
     */
    public double currentAverageBall() {
        return iTriedToStreamButMyHeadWasUnderWater(1, this.endedSemester);
    }

    /**
     * @param from the first semester.
     * @param to   the last semester.
     * @return average from|to ball.
     *
     * using stream(uncomfortable).
     */
    public double iTriedToStreamButMyHeadWasUnderWater(int from, int to) {
        return this.allSemesters.stream().flatMapToInt(s -> s.stream()
                .filter(s2 -> s2.semester >= from && s2.semester <= to)
                .mapToInt(a -> a.mark.getMark())).summaryStatistics().getAverage();
    }

    /**
     * @return boolean if marks in ended semester are 5.
     *
     * in my opinion its kinda strict rules.
     */
    public boolean moneyMoneyMoneyMustBeFunnyInTheRichMansWorld() {
        return this.allSemesters.stream().flatMap(semester -> semester.stream()
                        .filter(a -> a.semester == this.endedSemester))
                .allMatch(s -> s.mark == SubjectAndMark.Mark._5);
    }

    /**
     * @return motivation string.
     *
     * still strict.
     */
    public String kingInTheCastle() {
        if (iTriedToStreamButMyHeadWasUnderWater(1, this.endedSemester) >= 4.8
                && this.diploma.mark == SubjectAndMark.Mark._5) {
            return "You can do it";
        }
        return "Bruh";
    }
}