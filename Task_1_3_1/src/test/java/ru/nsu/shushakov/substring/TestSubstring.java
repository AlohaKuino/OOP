package ru.nsu.shushakov.substring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * test class.
 */
public class TestSubstring {
    @Test
    void smallTest() throws IOException {
        Hunter a = new Hunter("src/test/resources/smalInput.txt", "ab".toCharArray());
        a.find();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("24 ", testline);
    }

    @Test
    void smallTestNoLine() throws IOException {
        Hunter a = new Hunter("src/test/resources/smalInput.txt", "c".toCharArray());
        a.find();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("there is no such substring", testline);
    }

    @Test
    void bigEnglishGen() throws IOException {
        File input = new File("src/test/resources/input.txt");
        Gen testEnglishBig = new Gen(input);
        testEnglishBig.bigGenEnglishSymbols();
        Hunter a = new Hunter("src/test/resources/input.txt", "b".toCharArray());
        a.find();
        input.delete();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("0 6000001001 12000002002 ", testline);
    }

    @Test
    void berserk() throws IOException {
        File input = new File("src/test/resources/berserk.txt");
        Gen testEnglishBig = new Gen(input);
        testEnglishBig.bigGenJapanesePhrase();
        Hunter a = new Hunter("src/test/resources/berserk.txt", "ガッツ".toCharArray());
        a.find();
        input.delete();
        String testline = null;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/test/resources/answer.txt"))) {
            testline = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading error");
        }
        assertEquals("0 2070000003 5175000006 ", testline);
    }
}