package com.greenblat.adt.course_work.union;

public class DisjointSetUnion implements Union {

    private int[] parents;
    private int[] size;

    public DisjointSetUnion(int value) {
        this.parents = new int[value];
        this.size = new int[value];
        for (int i = 0; i < value; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public void makeSet(int v) {
        parents[v] = v;
        size[v] =1;
    }

    @Override
    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (size[x] < size[y]) {
                int tmp = y;
                y = x;
                x = tmp;
            }
            parents[y] = x;
            size[x] += size[y];
        }
    }


    // Эвристика
    @Override
    public int find(int v) {
        if (v == parents[v])
            return v;
        return parents[v] = find(parents[v]);
    }
}
