package ru.nsu.shushakov.notebook;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * enum for operations.
 */
public enum Operations {
    /**
     * adds note.
     */
    ADD {
        void action(List<String> args) throws IOException {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            Note newNote = new Note();
            newNote.setTitle((String) args.get(0));
            newNote.setBody((String) args.get(1));
            newNote.setTime(format.format(new Date()));
            File json = Paths.get("notes.json").toFile();
            ObjectMapper mapper = new ObjectMapper();
            List<Note> listNotes = new ArrayList(Arrays.asList((Note[]) mapper
                    .readValue(json, Note[].class)));
            listNotes.add(newNote);
            mapper.writerWithDefaultPrettyPrinter().writeValue(json, listNotes);
        }
    },
    /**
     * removes note.
     */
    RM {
        void action(List<String> args) throws IOException {
            File json = Paths.get("notes.json").toFile();
            ObjectMapper mapper = new ObjectMapper();
            List<Note> listNotes = new ArrayList(Arrays.asList((Note[]) mapper
                    .readValue(json, Note[].class)));
            List<Note> removed = listNotes.stream().filter((note) -> {
                return !note.getTitle().equals(args.get(0));
            }).toList();
            mapper.writerWithDefaultPrettyPrinter().writeValue(json, removed);
        }
    },
    /**
     * shows all or with params.
     */
    SHOW {
        public static final String ANSI_RESET = "\u001b[0m";
        public static final String ANSI_RED = "\u001b[31m";
        public static final String ANSI_GREEN = "\u001b[32m";

        void action(List<String> args) throws IOException, ParseException {
            File json = Paths.get("notes.json").toFile();
            ObjectMapper mapper = new ObjectMapper();
            List<Note> listNotes = new ArrayList(Arrays.asList((Note[]) mapper
                    .readValue(json, Note[].class)));
            if (args.size() >= 2) {
                SimpleDateFormat startAndEnd = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
                Date start = startAndEnd.parse(args.get(0));
                Date end = startAndEnd.parse(args.get(1));
                List<Note> tmp = new ArrayList<>();
                listNotes = listNotes.stream().filter(note -> {
                    try {
                        return startAndEnd.parse(note.getTime()).before(end) && startAndEnd
                                .parse(note.getTime()).after(start);
                    } catch (ParseException e) {
                        return false;
                    }
                }).toList();

                for (String subWord : args.subList(2, args.size())) {
                    tmp.addAll(listNotes.stream().filter(note -> note.getTitle().toLowerCase()
                            .contains(subWord.toLowerCase())).toList());
                }
                listNotes = tmp;
            }

            listNotes.forEach((note) -> {
                System.out.printf("\u001b[31m\n\t\t|TITLE|\n\u001b[0m\u001b[32m\t\t%s\n\u001b[0m" +
                        "\u001b[31m\t\t|NOTE|\n\u001b[0m\u001b[32m\t\t%s\n\u001b[0m\u001b[31m\t\t" +
                        "|TIME|\n\u001b[0m\u001b[32m\t\t%s\n\n\u001b[0m",
                        note.getTitle(), note.getBody(), note.getTime());
            });
        }
    };

    /**
     * empty constructor.
     */
    private Operations() {
    }

    /**
     * signature of function.
     *
     * @param var1 operation.
     * @throws IOException exception.
     * @throws ParseException exception.
     */
    abstract void action(List<String> var1) throws IOException, ParseException;
}
