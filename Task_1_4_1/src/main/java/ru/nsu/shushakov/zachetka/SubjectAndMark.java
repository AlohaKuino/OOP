package ru.nsu.shushakov.zachetka;

import java.util.List;

/**
 * helping class.
 */
public class SubjectAndMark {
    public String subject;
    public Mark mark;
    public boolean isItFinal;
    public int semester;
    public boolean isExam;
    public boolean isDiploma;
    public String themeOfDiploma;

    /**
     * @param subject  name of subject.
     * @param mark     mark.
     * @param semester number of semester.
     *                 <p>
     *                 if mark is final.
     */
    public SubjectAndMark(String subject, Mark mark, int semester) {
        this.subject = subject;
        this.mark = mark;
        this.semester = semester;
        this.isExam = true;
    }

    /**
     * @param subject   name of subject.
     * @param mark      mark.
     * @param semester  number of semester.
     * @param isItFinal boolean.
     *                  <p>
     *                  if mark is not final.
     */
    public SubjectAndMark(String subject, Mark mark, int semester, boolean isItFinal) {
        this.subject = subject;
        this.mark = mark;
        this.isItFinal = false;
        this.semester = semester;
        this.isExam = true;
    }

    /**
     * @param subject name of subject.
     * @param mark    mark.
     * @param theme   theme of diploma.
     *                <p>
     *                diploma constructor.
     */
    public SubjectAndMark(String subject, Mark mark, String theme) {
        this.subject = subject;
        this.mark = mark;
        this.themeOfDiploma = theme;
        this.isDiploma = true;
    }

    /**
     * enum for mark.
     */
    public enum Mark {
        _2(2),
        _3(3),
        _4(4),
        _5(5);
        private final int mark;

        private Mark(int mark) {
            this.mark = mark;
        }

        public int getMark() {
            return this.mark;
        }
    }
}
