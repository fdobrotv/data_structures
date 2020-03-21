package com.fdobbrotv.data_structures.leetcode.queue.number_of_islands;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberOfIslandsTest {
    @Test
    public void shouldFindOneIfEntireIslandAllocationTest() {
        char[][] grid = {
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'}
        };
        Solution solution = new Solution();
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    public void shouldNotFindAnyIslandsTest() {
        char[][] grid = {
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Solution solution = new Solution();
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    public void shouldPassWithDiagonalDistributionTest() {
        char[][] grid = {
                {'1', '0', '1', '0', '1'},
                {'0', '1', '0', '1', '0'},
                {'1', '0', '1', '0', '1'},
                {'0', '1', '0', '1', '0'}
        };
        Solution solution = new Solution();
        assertEquals(10, solution.numIslands(grid));
    }

    @Test
    public void shouldReturnOneIslandTest() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Solution solution = new Solution();
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    public void shouldReturnThreeIslandsTest() {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        Solution solution = new Solution();
        assertEquals(3, solution.numIslands(grid));
    }
}
