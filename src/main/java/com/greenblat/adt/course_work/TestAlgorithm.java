package com.greenblat.adt.course_work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestKraskal {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader  = new BufferedReader(new FileReader("src/main/java/com/greenblat/adt/course_work/resources/tree.txt"))){
            new Kraskal(reader).run();
        }
    }
}
