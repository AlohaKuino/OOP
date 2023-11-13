package ru.nsu.shushakov.substring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * class for generating big files.
 */
public class Gen {
    private File filename;

    public Gen(File name) {
        this.filename = name;
        filename.delete();
    }

    /**
     * generate ~11gb of english symbols.
     */
    protected void bigGenEnglishSymbols() {
        this.filename.delete();
        try {
            FileWriter writer = new FileWriter(this.filename, StandardCharsets.UTF_8);
            writer.write("b");
            for (long i = 0; i < 6_000_001_000L; i++) {
                writer.write("a");
            }
            writer.write("b");
            for (long i = 0; i < 6_000_001_000L; i++) {
                writer.write("a");
            }
            writer.write("b");
            writer.close();
        } catch (IOException e) {
            System.out.println("хихихи");
        }
    }

    /**
     * generate ~15gb of japanese symbols(it's opening from berserk).
     */
    protected void bigGenJapanesePhrase() {
        this.filename.delete();
        try {
            FileWriter writer = new FileWriter(this.filename, StandardCharsets.UTF_8);
            writer.write("ガッツ");
            for (long i = 0; i < 30_000_000; i++) {
                writer.write(
                        "この世界には 人の運命をつかさどる 何らかの超越的な 「律」 神の手が存在するのだろうか。"
                                + "少なくとも 人は自らの意志さえ 自由には出来ない");
            }
            writer.write("ガッツ");
            for (long i = 0; i < 45_000_000; i++) {
                writer.write(
                        "この世界には 人の運命をつかさどる 何らかの超越的な 「律」 神の手が存在するのだろうか。"
                                + "少なくとも 人は自らの意志さえ 自由には出来ない");
            }
            writer.write("ガッツ");
            writer.close();
        } catch (IOException e) {
            System.out.println("хихихи");
        }
    }
//    この世界には 人の運命をつかさどる 何らかの超越的な 「律」 神の手が存在するのだろうか。
//    少なくとも 人は自らの意志さえ 自由には出来ない。
}