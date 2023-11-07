package ru.nsu.shushakov.substring;

import java.io.*;

public class Hunter {
    String inputFile;
    String answerFile;
    char[] whatToFind;
    char[] currentLine;

    FileWriter t;

    public Hunter(String file, char[] subStr) {
        this.inputFile = file;
        this.whatToFind = subStr;
        this.answerFile = "src/main/assets/answer.txt";
        File tmpFile = new File(this.answerFile);
        tmpFile.delete();
    }

    public void find(){
        int i = 0;
        char[] line = new char[10_000_000];
        try (BufferedReader reader = new BufferedReader(new FileReader(this.inputFile))) {
            while(reader.read(line, 0, 10_000_000) > -1){
                this.currentLine = line;
                this._KnuthMorrisPratt(i);
                i ++;
            }
        }
        catch (IOException e) {
            System.out.println("Reading error");
        }
    }

    public void _KnuthMorrisPratt(int counter) {
        int[] pfl = pfl();
        int k = 0;
        for (int i = 0; i < this.currentLine.length; ++i) {
            while (this.whatToFind[k] != this.currentLine[i] && k > 0) {
                k = pfl[k - 1];
            }
            if(this.currentLine[i] == '\u0000')
                continue;
            if (this.whatToFind[k] == this.currentLine[i]) {
                k = k + 1;
                if (k == this.whatToFind.length) {
                    answerWriter(i + 1 - k + counter * 10_000_000);
                    k = pfl[k - 1];
                }
            }
            else {
                k = 0;
            }
        }
    }

    public int[] pfl() {
        int[] pfl = new int[this.whatToFind.length];
        pfl[0] = 0;
        for (int i = 1; i < this.whatToFind.length; ++i) {
            int k = pfl[i - 1];
            while (this.whatToFind[k] != this.whatToFind[i] && k > 0) {
                k = pfl[k - 1];
            }
            if (this.whatToFind[k] == this.whatToFind[i]) {
                pfl[i] = k + 1;
            }
            else {
                pfl[i] = 0;
            }
        }
        return pfl;
    }
    public static void answerWriter(int index){
        try {
            FileWriter writer = new FileWriter("src/main/assets/answer.txt", true);
            writer.write(String.valueOf(index) + ", ");
            writer.close();
        } catch (IOException e) {
            System.out.println("Writing error");
        }
    }
}
