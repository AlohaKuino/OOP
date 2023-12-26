package ru.nsu.shushakov.notebook;

/**
 * class fpr a note in my notebook.
 */
public class Note {
    private String title;
    private String body;
    private String time;

    /**
     * empty constructor.
     */
    public Note() {
    }

    /**
     * getter.
     *
     * @return title of the note.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * setter.
     *
     * @param title of future note.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter.
     *
     * @return main information of the note.
     */
    public String getBody() {
        return this.body;
    }

    /**
     * setter.
     *
     * @param body sets main information of the body.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * getter.
     *
     * @return time when note was made.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * setter.
     *
     * @param time sets time when note was made.
     */
    public void setTime(String time) {
        this.time = time;
    }
}
