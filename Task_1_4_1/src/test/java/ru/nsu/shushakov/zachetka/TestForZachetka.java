package ru.nsu.shushakov.zachetka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark.FIVE;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark.FOUR;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark.THREE;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark.TWO;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * test class.
 */
public class TestForZachetka {
    @Test
    public void testAllAreFinalCantGetUniqueDiploma() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", TWO, 1);
        SubjectAndMark a1 = new SubjectAndMark("OOP", TWO, 1);
        SubjectAndMark b = new SubjectAndMark("OSI", THREE, 2);
        SubjectAndMark c = new SubjectAndMark("Eng", FOUR, 3);
        SubjectAndMark z = new SubjectAndMark("Eng", FOUR, 4);
        SubjectAndMark diploma = new SubjectAndMark("Eng", FIVE, "Irish accent Analysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);
        e.add(diploma);
        Zachetka d = new Zachetka("HEHE HIHI", 22213, 6, e);
        assertEquals(2.0, d.averageBallInFromToSemesters(1, 1));
        assertTrue(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
        assertEquals(3.0, d.currentAverageBall());
        assertEquals("Bruh", d.uniqueDiploma());
        assertTrue(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
    }

    @Test
    public void exceptionTest() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", TWO, 1);
        SubjectAndMark a1 = new SubjectAndMark("OOP", TWO, 1);
        SubjectAndMark b = new SubjectAndMark("OSI", THREE, 2);
        SubjectAndMark c = new SubjectAndMark("Eng", FOUR, 3);
        SubjectAndMark z = new SubjectAndMark("Eng", TWO, 4);
        SubjectAndMark diploma = new SubjectAndMark("Eng", FIVE, "Irish accent Analysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);
        e.add(diploma);

        Exception exception = assertThrows(SemesterException.class, () -> {
            Zachetka d = new Zachetka("HEHE HIHI", 22213, 9, e);
        });

        String expectedMessage = "\n\n are you this old? \n";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    public void averageIsZero() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", TWO, 5, false);
        SubjectAndMark a1 = new SubjectAndMark("OOP", TWO, 1, false);
        SubjectAndMark b = new SubjectAndMark("OSI", THREE, 2, false);
        SubjectAndMark c = new SubjectAndMark("Eng", FOUR, 3, false);
        SubjectAndMark z = new SubjectAndMark("Eng", TWO, 4, false);
        SubjectAndMark diploma = new SubjectAndMark("Eng", FIVE, "Irish accent Anal,ysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);
        e.add(diploma);
        Zachetka d = new Zachetka("HEHE HIHI", 22213, 5, e);
        assertEquals("Bruh", d.uniqueDiploma());
        assertEquals(0.0, d.averageBallInFromToSemesters(1, 1));
        assertFalse(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
        assertEquals(0.0, d.currentAverageBall());
        assertFalse(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
    }

    @Test
    public void canGetUniqueDiploma() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", FIVE, 1);
        SubjectAndMark a1 = new SubjectAndMark("OOP", FIVE, 2);
        SubjectAndMark b = new SubjectAndMark("OSI", FIVE, 3);
        SubjectAndMark c = new SubjectAndMark("Eng", FOUR, 4);
        SubjectAndMark z = new SubjectAndMark("Eng", FIVE, 5);
        SubjectAndMark diploma = new SubjectAndMark("Eng", FIVE, "Irish accent Anal,ysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);
        e.add(diploma);
        Zachetka d = new Zachetka("HEHE HIHI", 22213, 5, e);
        assertEquals("You can do it", d.uniqueDiploma());
        assertEquals(5.0, d.averageBallInFromToSemesters(1, 1));
        assertTrue(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
        assertEquals(4.8, d.currentAverageBall());
        assertTrue(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
    }

    @Test
    public void cantFetAnything() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", TWO, 1);
        SubjectAndMark a1 = new SubjectAndMark("OOP", TWO, 2);
        SubjectAndMark b = new SubjectAndMark("OSI", TWO, 3);
        SubjectAndMark c = new SubjectAndMark("Eng", TWO, 4);
        SubjectAndMark z = new SubjectAndMark("Eng", TWO, 5);
        SubjectAndMark diploma = new SubjectAndMark("Eng", TWO, "Irish accent Anal,ysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);
        e.add(diploma);
        Zachetka d = new Zachetka("HEHE HIHI", 22213, 5, e);
        assertEquals("Bruh", d.uniqueDiploma());
        assertEquals(2.0, d.averageBallInFromToSemesters(1, 1));
        assertFalse(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
        assertEquals(2.0, d.currentAverageBall());
        assertFalse(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
    }
}
