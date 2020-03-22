package com.fdobbrotv.data_structures.leetcode.queue.open_the_lock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenTheLockTest {
    @Test
    public void shouldFailOnDeadendEqualsZeroesTest() {
        String[] deadends = new String[]{"0000"};
        String target = "8888";
        Solution solution = new Solution();
        assertEquals(-1, solution.openLock(deadends, target));
    }

    @Test
    public void shouldFindSolutionInOneMoveTest() {
        String[] deadends = new String[]{"8888"};
        String target = "0009";
        Solution solution = new Solution();
        assertEquals(1, solution.openLock(deadends, target));
    }

    @Test
    public void shouldFindIndirectSolutionTest() {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        Solution solution = new Solution();
        assertEquals(4, solution.openLock(deadends, target));
    }
}
