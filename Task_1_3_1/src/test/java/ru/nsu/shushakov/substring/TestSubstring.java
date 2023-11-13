package ru.nsu.shushakov.substring;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSubstring {
    @Test
    void smallTest() throws IOException {
        Hunter a = new Hunter("src/main/resources/smalInput.txt", "ab".toCharArray());
        a.find();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("24 ", testline);
    }

    @Test
    void bigEnglishGen() throws IOException {
        File input = new File("src/main/resources/input.txt");
        Gen testEnglishBig = new Gen(input);
        testEnglishBig.bigGenEnglishSymbols();
        Hunter a = new Hunter("src/main/resources/input.txt", "b".toCharArray());
        a.find();
        input.delete();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("0 6000001001 12000002002 ", testline);
    }

    @Test
    void berserk() throws IOException {
        File input = new File("src/main/resources/berserk.txt");
        Gen testEnglishBig = new Gen(input);
        testEnglishBig.bigGenJapanesePhrase();
        Hunter a = new Hunter("src/main/resources/berserk.txt", "ガッツ".toCharArray());
        a.find();
        input.delete();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("0 2070000003 5175000006 ", testline);
    }
}