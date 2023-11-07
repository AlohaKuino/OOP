package ru.nsu.shushakov.substring;

import java.io.FileWriter;
import java.io.IOException;

public class Gen {
    public static void ab(){
        try {
            FileWriter writer = new FileWriter("src/main/assets/large.txt", true);

            for(long i = 0; i < 1100000100L; i ++){
                writer.write("a");
            }
            writer.write("b");

            writer.close();
        } catch (IOException e) {
            System.out.println("хихихи");
        }
    }
}
