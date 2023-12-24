package ru.nsu.shushakov.notebook;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * main class.
 */
public class Notebook {
    @Option(
            name = "-add",
            usage = "adds note to the notebook EXAMPLE: -add \"title\" \"some information\"\n"
    )
    private boolean add;
    @Option(
            name = "-rm",
            usage = "removes note from the notebook EXAMPLE: -rm \"title of note you want to"
                    + " remove\"\n"
    )
    private boolean remove;
    @Option(
            name = "-show",
            usage = "shows the whole notebook(for now) EXAMPLE: -show\n"
    )
    private boolean show;
    @Argument
    private List<String> arguments = new ArrayList();

    /**
     * empty constructor.
     */
    public Notebook() {
    }

    /**
     * just like in args4j github.
     *
     * @param args line of args.
     * @throws IOException exception.
     * @throws ParseException exception.
     */
    public static void main(String[] args) throws IOException, ParseException {
        (new Notebook()).doMain(args);
    }

    /**
     * just like in args4j github.
     *
     * @param args line of args.
     * @throws IOException  exception.
     * @throws ParseException  exception.
     */
    public void doMain(String[] args) throws IOException, ParseException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (this.arguments.isEmpty() && !this.show) {
                throw new CmdLineException(parser, "No argument is given");
            }
        } catch (CmdLineException var4) {
            parser.printUsage(System.err);
            return;
        }

        Operations todo = null;
        if (this.add) {
            todo = Operations.ADD;
        }

        if (this.show) {
            todo = Operations.SHOW;
        }

        if (this.remove) {
            todo = Operations.RM;
        }

        assert todo != null;

        todo.action(this.arguments);
        this.arguments.clear();
    }
}
