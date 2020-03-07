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

    @Test
    public void testCircularQueueCapacity() {
        MyCircularQueue queue = new MyCircularQueue(30);
        assertTrue(queue.enQueue(71));
        assertFalse(queue.isFull());
        assertTrue(queue.enQueue(32));
        assertTrue(queue.enQueue(1));
        assertFalse(queue.isFull());
        assertTrue(queue.enQueue(32));
        assertTrue(queue.enQueue(8));
        assertTrue(queue.enQueue(6));
        assertEquals(71, queue.Front());
        assertEquals(71, queue.Front());
        assertEquals(6, queue.Rear());
        assertTrue(queue.enQueue(8));
        assertEquals(8, queue.Rear());
        assertTrue(queue.enQueue(3));
        assertEquals(3, queue.Rear());
        assertEquals(71, queue.Front());
        assertTrue(queue.enQueue(56));
        assertTrue(queue.enQueue(41));
        assertEquals(71, queue.Front());
        assertTrue(queue.enQueue(14));
        assertTrue(queue.enQueue(6));
        assertEquals(6, queue.Rear());
        assertTrue(queue.enQueue(25));
        assertFalse(queue.isEmpty());
        assertEquals(25, queue.Rear());
        assertEquals(71, queue.Front());
        assertEquals(25, queue.Rear());
        assertTrue(queue.enQueue(44));
        assertEquals(71, queue.Front());
        assertTrue(queue.enQueue(84));
        assertEquals(84, queue.Rear());
        assertFalse(queue.isEmpty());
        assertEquals(84, queue.Rear());
        assertTrue(queue.enQueue(59));
        assertEquals(71, queue.Front());
        assertEquals(71, queue.Front());

        assertTrue(queue.deQueue());
        assertTrue(queue.enQueue(4));
        assertEquals(32, queue.Front());

        assertTrue(queue.enQueue(40));
        assertTrue(queue.enQueue(11));

        assertTrue(queue.deQueue());
        assertTrue(queue.enQueue(94));
        assertFalse(queue.isFull());
        assertEquals(1, queue.Front());
        assertTrue(queue.enQueue(72));

        assertTrue(queue.deQueue());
        assertTrue(queue.enQueue(19));
        assertFalse(queue.isEmpty());
        assertFalse(queue.isEmpty());
        assertTrue(queue.enQueue(20));
        assertEquals(32, queue.Front());
        assertEquals(20, queue.Rear());

        assertTrue(queue.deQueue());
        assertEquals(8, queue.Front());
        assertTrue(queue.enQueue(58));
        assertEquals(58, queue.Rear());
        assertTrue(queue.enQueue(54));
        assertEquals(54, queue.Rear());
        assertEquals(54, queue.Rear());
        assertEquals(8, queue.Front());

        assertTrue(queue.deQueue());
        assertTrue(queue.enQueue(65));

        assertTrue(queue.deQueue());
        assertEquals(65, queue.Rear());
    }
}
