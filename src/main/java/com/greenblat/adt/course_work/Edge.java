package com.greenblat.adt.course_work;

public class Edge{

    private final int firstVertex;
    private final int secondVertex;
    private final int weight;

    public Edge(int firstVertex, int secondVertex, int weight) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getFirstVertex() {
        return firstVertex;
    }

    public int getSecondVertex() {
        return secondVertex;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "firstVertex=" + firstVertex +
                ", secondVertex=" + secondVertex +
                ", weight=" + weight +
                '}';
    }
}
