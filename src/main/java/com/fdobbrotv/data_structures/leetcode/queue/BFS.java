package com.fdobbrotv.data_structures.leetcode.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public void search(Graph graph, String root) {
        Queue<String> needToCheck = new LinkedList<>();
        List<String> visited = new LinkedList<>();

        needToCheck.add(root);
        while (!needToCheck.isEmpty()) {
            String vertex = needToCheck.poll();
            for (Vertex v : graph.getAdjacentVertices(vertex)) {
                if (!visited.contains(v.getValue())) {
                    visited.add(v.getValue());
                    needToCheck.add(v.getValue());
                }
            }
        }
    }
}

