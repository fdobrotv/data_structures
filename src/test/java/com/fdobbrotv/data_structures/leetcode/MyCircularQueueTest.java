package com.fdobbrotv.data_structures.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyCircularQueueTest {
    @Test
    public void testCircularQueue() {
        MyCircularQueue queue = new MyCircularQueue(4);
        assertTrue(queue.enQueue(3));
        assertTrue(queue.enQueue(1));
        assertTrue(queue.enQueue(2));
        assertTrue(queue.enQueue(3));

        assertEquals(3, queue.Rear());
        assertTrue(queue.isFull());
        assertEquals(3, queue.Front());
        assertTrue(queue.deQueue());
        assertEquals(1, queue.Front());
        assertTrue(queue.deQueue());
        assertEquals(2, queue.Front());
        assertTrue(queue.enQueue(4));
        assertEquals(4, queue.Rear());
    }

    @Test
    public void testCircularQueueEmptyState() {
        MyCircularQueue queue = new MyCircularQueue(4);
        assertTrue(queue.enQueue(6));
        assertEquals(6, queue.Rear());
        assertEquals(6, queue.Rear());
        assertTrue(queue.deQueue());
        assertTrue(queue.enQueue(5));
        assertEquals(5, queue.Rear());
        assertTrue(queue.deQueue());
        assertEquals(-1, queue.Front());
        assertFalse(queue.deQueue());
        assertFalse(queue.deQueue());
        assertFalse(queue.deQueue());
    }
}
