package com.greenblat.adt.course_work;

import com.greenblat.adt.course_work.union.DisjointSetUnion;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kraskal {

    private  List<String> vertices;
    private List<Edge> edges;

    public Kraskal(BufferedReader reader) throws IOException {
        createTreeFromFile(reader);
    }

    private void createTreeFromFile(BufferedReader reader) throws IOException {
        vertices = getValuesOfTree(reader);

        edges = new ArrayList<>();
        for (int first = 0; first < vertices.size(); first++) {
            List<String> valueOfEdges = getValuesOfTree(reader);
            for (int second = first + 1; second < valueOfEdges.size(); second++) {
                edges.add(new Edge(first, second, Integer.parseInt(valueOfEdges.get(second))));
            }

        }
    }

    public void run() {
        int sumWeight = 0;
        List<Edge> result = new ArrayList<>();
        edges.sort(Comparator.comparingInt(Edge::getWeight));
        DisjointSetUnion set = new DisjointSetUnion(vertices.size());
        for (Edge edge : edges) {
            if (set.find(edge.getFirstVertex()) != set.find(edge.getSecondVertex())) {
                sumWeight += edge.getWeight();
                set.union(edge.getFirstVertex(), edge.getSecondVertex());
                result.add(edge);
            }

        }

        System.out.println(result);
        System.out.println(sumWeight);

    }

    private List<String> getValuesOfTree(BufferedReader reader) throws IOException {
        String[] values = reader.readLine().split(" ");
        List<String> vertices = new ArrayList<>();
        for (String value : values) {
            if (!value.equals(""))
                vertices.add(value);
        }
        return vertices;
    }
}
