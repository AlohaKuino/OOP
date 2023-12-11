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
     * constructor.
     *
     * @param nameOfStudent                 students name.
     * @param numberOfTheGroup              group number.
     * @param endedSemester                 semester that is ended.
     * @param streamOfSuspiciousInformation array of subjects and marks.
     * @throws SemesterException if semester greater 8 then it will be thrown.
     */
    public Zachetka(String nameOfStudent, int numberOfTheGroup, int endedSemester,
                    List<SubjectAndMark> streamOfSuspiciousInformation)
            throws SemesterException {
        this.nameOfStudent = nameOfStudent;
        this.endedSemester = endedSemester;
        if (this.endedSemester > 8 || this.endedSemester < 1) {
            throw new SemesterException();
        }
        this.allSemesters = new ArrayList<>();
        setAllSemesters(streamOfSuspiciousInformation);
    }

    /**
     * creating an array of arrays without nullPointerException.
     *
     * @param streamOfSuspiciousInformation array of subjects and marks.
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
     * using iTriedToStreamButMyHeadWasUnderWater.
     *
     * @return average ball from the beninging to the ended semester.
     */
    public double currentAverageBall() {
        return averageBallInFromToSemesters(1, this.endedSemester);
    }

    /**
     * using stream(uncomfortable).
     *
     * @param from the first semester.
     * @param to   the last semester.
     * @return average from|to ball.
     */
    public double averageBallInFromToSemesters(int from, int to) {
        return this.allSemesters.stream().flatMapToInt(s -> s.stream()
                .filter(s2 -> s2.semester >= from && s2.semester <= to && s2.isItFinal)
                .mapToInt(a -> a.mark.getMark())).summaryStatistics().getAverage();
    }

    /**
     * in my opinion its kinda strict rules.
     *
     * @return boolean if marks in ended semester are 5.
     */
    public boolean moneyMoneyMoneyMustBeFunnyInTheRichMansWorld() {
        return this.allSemesters.stream().flatMap(semester -> semester.stream()
                        .filter(a -> a.semester == this.endedSemester))
                .allMatch(s -> s.mark == SubjectAndMark.Mark.FIVE);
    }

    /**
     * still strict.
     *
     * @return motivation string.
     */
    public String uniqueDiploma() {
        if (averageBallInFromToSemesters(1, this.endedSemester) >= 4.8
                && this.diploma.mark == SubjectAndMark.Mark.FIVE) {
            return "You can do it";
        }
        return "Bruh";
    }
}