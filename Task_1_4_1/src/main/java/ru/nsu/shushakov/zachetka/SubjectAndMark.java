package ru.nsu.shushakov.zachetka;

/**
 * helping class.
 */
public class SubjectAndMark {
    public String subject;
    public Mark mark;
    public boolean isItFinal;
    public int semester;
    public boolean isDiploma;
    public String themeOfDiploma;

    /**
     * if mark is final.
     *
     * @param subject  name of subject.
     * @param mark     mark.
     * @param semester number of semester.
     */
    public SubjectAndMark(String subject, Mark mark, int semester) {
        this.subject = subject;
        this.mark = mark;
        this.semester = semester;
        this.isItFinal = true;
    }

    /**
     * if mark is not final.
     *
     * @param subject   name of subject.
     * @param mark      mark.
     * @param semester  number of semester.
     * @param isItFinal boolean.
     */
    public SubjectAndMark(String subject, Mark mark, int semester, boolean isItFinal) {
        this.subject = subject;
        this.mark = mark;
        this.isItFinal = false;
        this.semester = semester;
    }

    /**
     * diploma constructor.
     *
     * @param subject name of subject.
     * @param mark    mark.
     * @param theme   theme of diploma.
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
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5);
        private final int mark;

        private Mark(int mark) {
            this.mark = mark;
        }

        public int getMark() {
            return this.mark;
        }
    }
}