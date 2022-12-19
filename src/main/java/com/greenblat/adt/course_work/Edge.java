package com.greenblat.adt.course_work;

public class Edge implements Comparable<Edge> {

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

    public String getVerticesString() {
        return ((char) (firstVertex + 65)) + " " + ((char) (secondVertex + 65));
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
