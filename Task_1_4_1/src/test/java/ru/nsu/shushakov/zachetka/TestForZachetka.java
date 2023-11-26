package ru.nsu.shushakov.zachetka;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static ru.nsu.shushakov.zachetka.SubjectAndMark.Mark.*;

public class TestForZachetka {
    @Test
    public void firstTest() throws SemesterException {
        SubjectAndMark a = new SubjectAndMark("OOP", _2, 1);
        SubjectAndMark a1 = new SubjectAndMark("OOP", _2, 1);
        SubjectAndMark b = new SubjectAndMark("OSI", _3, 2);
        SubjectAndMark c = new SubjectAndMark("Eng", _4, 3);
        SubjectAndMark z = new SubjectAndMark("Eng", _4, 4);
//        SubjectAndMark diploma = new SubjectAndMark("Eng", _5, "Irish accent Analysis");
        ArrayList<SubjectAndMark> e = new ArrayList<>();
        e.add(a);
        e.add(b);
        e.add(a1);
        e.add(c);
        e.add(z);

//        e.add(diploma);
        Zachetka d = new Zachetka("HEHE HIHI", 22213, 9, e);
        System.out.println(d.iTriedToStreamButMyHeadWasUnderWater(1, 1));
        System.out.println(d.moneyMoneyMoneyMustBeFunnyInTheRichMansWorld());

//        System.out.println(d.currentAverageBall());
//        System.out.println(d.kingInTheCastle());
//        System.out.println(d.moneyMoneyMoney());
//        System.out.println(d.diploma.themeOfDiploma);
    }
}
