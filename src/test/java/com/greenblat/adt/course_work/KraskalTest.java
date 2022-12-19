package com.greenblat.adt.course_work;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KraskalTest {

    List<String> createTree(String fileName) {
        var minSpanningTree = TestAlgorithm.getEdgeListFromFile(fileName);
        List<String> edges = new ArrayList<>();
        for (int i = 0; i < minSpanningTree.size(); i++) {
            edges.add(minSpanningTree.get(i).getVerticesString());
        }
        return edges;
    }

    @Test
    void checkSmallTree() {
        List<String> tree = createTree("small_tree.txt");
        List<String> expected = new ArrayList<>(new String[]{"A C", "B C"});
        assertEquals(expected, tree);
    }

    @Test
    void checkBigTree() {
        List<String> tree = createTree("big_tree.txt");
        List<String> expected = new ArrayList<>(new String[]{"A B", "B C", "C E", "D E", "C F", "F G"});
        assertEquals(expected, tree);
    }

    @Test
    void checkWikiTree() {
        List<String> tree = createTree("wiki_tree.txt");
        List<String> expected = new ArrayList<>(new String[]{"A D", "C E", "D F", "A B", "B E", "E G"});
        assertEquals(expected, tree);
    }

    @Test
    void checkHabrTree() {
        List<String> tree = createTree("habr_tree.txt");
        List<String> expected = new ArrayList<>(new String[]{"B D", "C D", "A B", "C E", "D F"});
        assertEquals(expected, tree);
    }



}