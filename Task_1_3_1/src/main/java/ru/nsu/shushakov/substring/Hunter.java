package ru.nsu.shushakov.substring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * main class.
 */
public class Hunter {
    private char[] whatToFind;
    private char[] currentLine;
    private String inputFileName;
    private String answerFileName;
    File inputFile;
    private File answerFile;
    private static final int bufferSize = 10_000_000;

    /**
     * @param file   input file.
     * @param subStr substring we need to find.
     */
    public Hunter(String file, char[] subStr) {
        this.inputFileName = file;
        this.inputFile = new File(this.inputFileName);
        this.whatToFind = subStr;
        this.answerFileName = "src/main/resources/answer.txt";
        this.answerFile = new File(this.answerFileName);
        this.answerFile.delete();
    }

    /**
     * opens file, reads 10_000_000 chars and calls kmp alg.
     */
    protected void find() throws IOException {
        int i = 0;
        char[] line = new char[bufferSize];
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.inputFileName);
        if (inputStream == null) {
            inputStream = new FileInputStream(this.inputFileName);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        while (reader.read(line, 0, bufferSize) > -1) {
            this.currentLine = line;
            this._KnuthMorrisPratt(i);
            i++;
        }
    }

    /**
     * @param counter how many buffers we read.
     */
    private void _KnuthMorrisPratt(int counter) {
        int[] pfl = prefix();
        int k = 0;
        for (int i = 0; i < this.currentLine.length; ++i) {
            while (this.whatToFind[k] != this.currentLine[i] && k > 0) {
                k = pfl[k - 1];
            }
            if (this.currentLine[i] == '\u0000') {
                break;
            }
            if (this.whatToFind[k] == this.currentLine[i]) {
                k++;
                if (k == this.whatToFind.length) {
                    answerWriter(i + 1 - k + (long) counter * bufferSize);
                    k = pfl[k - 1];
                }
            } else {
                k = 0;
            }
        }
    }

    /**
     * @return array of maximal lengths of equal suffixes and prefixes for i'th symbol in substring.
     */
    private int[] prefix() {
        int[] pfl = new int[this.whatToFind.length];
        pfl[0] = 0;
        for (int i = 1; i < this.whatToFind.length; ++i) {
            int k = pfl[i - 1];
            while (this.whatToFind[k] != this.whatToFind[i] && k > 0) {
                k = pfl[k - 1];
            }
            if (this.whatToFind[k] == this.whatToFind[i]) {
                pfl[i] = k + 1;
            } else {
                pfl[i] = 0;
            }
        }
        return pfl;
    }

    /**
     * @param index what to write to the file.
     */
    private void answerWriter(long index) {
        try {
            FileWriter writer = new FileWriter(this.answerFileName, true);
            writer.write(index + " ");
            writer.close();
        } catch (IOException e) {
            System.out.println("Writing error");
        }
    }
}