package com.fdobbrotv.data_structures.leetcode.queue.number_of_islands;

        import java.util.*;

public class Solution {
    Set<Coordinate> visitedIslandCoordinates = new HashSet<>();  // store all the nodes that we've islands

    public int numIslands(char[][] grid) {
        System.out.println("Input is:");
        printGrid(grid);

        int numberOfIslands = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col] + "\t");
                if (!visitedIslandCoordinates.add(new Coordinate(row, col)))
                    continue;
                visitedIslandCoordinates.add(new Coordinate(row, col));
                if (grid[row][col] == '1') {
                    numberOfIslands++;

                    expandIsland(grid, row, col);
                }
            }
            System.out.println();
        }

        System.out.println("Found " + numberOfIslands + " islands");
        return numberOfIslands;
    }

    private void expandIsland(char[][] grid, int row, int col) {
        boolean isRightAvailable = row + 1 < grid.length;
        if (isRightAvailable && !visitedIslandCoordinates.contains(new Coordinate(row + 1, col))) {
            visitedIslandCoordinates.add(new Coordinate(row + 1, col));
            if (grid[row + 1][col] == '1') {
                expandIsland(grid, row + 1, col);
            }
        }

        boolean isDownAvailable = col + 1 < grid[row].length;
        if (isDownAvailable && !visitedIslandCoordinates.contains(new Coordinate(row, col + 1))) {
            visitedIslandCoordinates.add(new Coordinate(row, col + 1));
            if (grid[row][col + 1] == '1') {
                expandIsland(grid, row, col + 1);
            }
        }

        boolean isLeftAvailable = row - 1 >= 0;
        if (isLeftAvailable && !visitedIslandCoordinates.contains(new Coordinate(row - 1, col))) {
            visitedIslandCoordinates.add(new Coordinate(row - 1, col));
            if (grid[row - 1][col] == '1') {
                expandIsland(grid, row - 1, col);
            }
        }

        boolean isUpAvailable = col - 1 >= 0;
        if (isUpAvailable && !visitedIslandCoordinates.contains(new Coordinate(row, col - 1))) {
            visitedIslandCoordinates.add(new Coordinate(row, col - 1));
            if (grid[row][col - 1] == '1') {
                expandIsland(grid, row, col - 1);
            }
        }
    }

    private void printGrid(char[][] grid) {
        //I could use System.out.println(Arrays.deepToString(grid));
        for (char[] row : grid) {
            for (char col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    class Coordinate {
        private int row, col;

        Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row &&
                    col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}