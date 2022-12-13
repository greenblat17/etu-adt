package com.greenblat.adt.course_work;

import com.greenblat.adt.course_work.union.DisjointSetUnion;
import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab2.sorts.MergeSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;

public class Kraskal {

    private List<String> vertices;
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
                int weight = Integer.parseInt(valueOfEdges.get(second));
                if (weight > 0)
                    edges.add(new Edge(first, second, weight));
            }

        }
    }

    public List<Edge> run() {
        List<Edge> tree = new ArrayList<>();
        new MergeSort<>(edges).sort();
        DisjointSetUnion set = new DisjointSetUnion(vertices.size());
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (set.find(edge.getFirstVertex()) != set.find(edge.getSecondVertex())) {
                set.union(edge.getFirstVertex(), edge.getSecondVertex());
                tree.add(edge);
            }
        }
        return tree;
    }

    private List<String> getValuesOfTree(BufferedReader reader) throws IOException {
        String[] values = reader.readLine().split("\\s+");
        List<String> vertices = new ArrayList<>();
        for (String value : values) {
            if (!value.equals(""))
                vertices.add(value);
        }
        return vertices;
    }
}
