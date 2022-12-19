package com.greenblat.adt.course_work;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestAlgorithm {

    private final static String PATH = "src/test/resources/";

    public static void main(String[] args) {

        String fileName = "wiki_tree.txt";
        List<Edge> minSpanningTree = getEdgeListFromFile(fileName);

        int sumWeight = 0;
        for (int i = 0; i < minSpanningTree.size(); i++) {
            Edge edge = minSpanningTree.get(i);
            System.out.println(edge.getVerticesString());
            sumWeight += minSpanningTree.get(i).getWeight();
        }

        System.out.println(sumWeight);
    }

    public static List<Edge> getEdgeListFromFile(String fileName) {
        List<Edge> minSpanningTree = new ArrayList<>();
        try (BufferedReader reader  = new BufferedReader(new FileReader(PATH + fileName))){
            minSpanningTree = new Kraskal(reader).run();
        } catch (IOException e){
            e.printStackTrace();
        }
        return minSpanningTree;
    }
}
