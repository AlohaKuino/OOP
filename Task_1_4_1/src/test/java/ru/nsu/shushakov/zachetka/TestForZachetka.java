package ru.nsu.shushakov.zachetka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark._2;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark._3;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark._4;
import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark._5;

public class TestForZachetka {
    @Test
    public void firstTest() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", _2, 1);
        SubjectAndMark a1 = new SubjectAndMark("OOP", _2, 1);
        SubjectAndMark b = new SubjectAndMark("OSI", _3, 2);
        SubjectAndMark c = new SubjectAndMark("Eng", _4, 3);
        SubjectAndMark z = new SubjectAndMark("Eng", _4, 4);
        SubjectAndMark diploma = new SubjectAndMark("Eng", _5, "Irish accent Analysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);
        e.add(diploma);
        Zachetka d = new Zachetka("HEHE HIHI", 22213, 6, e);
        assertEquals(2.0, d.triedToStreamButMyHeadWasUnderWater(1, 1));
        assertTrue(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
        assertEquals(3.0, d.currentAverageBall());
        assertEquals("Bruh", d.kingInTheCastle());
        assertTrue(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());
    }
}
