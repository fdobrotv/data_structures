package com.fdobbrotv.data_structures.leetcode.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Vertex>> elements;

    public Graph(Map<Vertex, List<Vertex>> elements) {
        this.elements = elements;
    }

    public void addVertex(String label) {
        elements.putIfAbsent(new Vertex(label), new ArrayList<>());
    }

    public void removeVertex(String label) {
        Vertex v = new Vertex(label);
        elements.values().forEach(e -> e.remove(v));
        elements.remove(new Vertex(label));
    }

    // Adds an edge to an undirected graph
    public void addEdge(String leftValue, String rightValue) {
        Vertex leftVertex = new Vertex(leftValue);
        Vertex rightVertex = new Vertex(rightValue);
        elements.get(leftVertex).add(rightVertex);
        elements.get(rightVertex).add(leftVertex);
    }

    public void removeEdge(String leftValue, String rightValue) {
        Vertex leftVertex = new Vertex(leftValue);
        Vertex rightVertex = new Vertex(rightValue);
        List<Vertex> leftVertexAdjacency = elements.get(leftVertex);
        List<Vertex> rightVertexAdjacency = elements.get(rightVertex);
        if (leftVertexAdjacency != null)
            leftVertexAdjacency.remove(rightVertex);
        if (rightVertexAdjacency != null)
            rightVertexAdjacency.remove(leftVertex);
    }

    public List<Vertex> getAdjacentVertices(String value) {
        return elements.get(new Vertex(value));
    }
}

