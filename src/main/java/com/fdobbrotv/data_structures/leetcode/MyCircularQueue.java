package com.fdobbrotv.data_structures.leetcode;

class MyCircularQueue {
    private int[] arrayOfInt;
    private int frontPointer;
    private int rearPointer;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        arrayOfInt = new int[k];
        frontPointer = rearPointer = -1;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (isFull()) return false;

        if (isEmpty()) {
            ++rearPointer;
            ++frontPointer;
            arrayOfInt[rearPointer] = value;
            return true;
        } else {
            int maxIndexOfArray = arrayOfInt.length - 1;
            if (rearPointer < maxIndexOfArray) {
                ++rearPointer;
                arrayOfInt[rearPointer] = value;
                return true;
            } else if (rearPointer == maxIndexOfArray && frontPointer > 0) {
                rearPointer = 0;
                arrayOfInt[rearPointer] = value;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) return false;

        if (frontPointer < rearPointer) {
            if (frontPointer < arrayOfInt.length) {
                frontPointer++;
            } else {
                frontPointer = 0;
            }
            return true;
        } else if (frontPointer > rearPointer) {
            if (frontPointer < arrayOfInt.length) {
                frontPointer++;
            } else {
                frontPointer = 0;
            }
            return true;
        } else {
            frontPointer = rearPointer = -1;
            return true;
        }
    }


    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (!isEmpty()) {
            return arrayOfInt[frontPointer];
        } else {
            return -1;
        }
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (!isEmpty()) {
            return arrayOfInt[rearPointer];
        } else {
            return -1;
        }
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return frontPointer == -1;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return lengthUsed() == arrayOfInt.length;
    }

    /**
     * Get count of used indexes
     */
    private int lengthUsed() {
        if (frontPointer < rearPointer) {
            return frontPointer + rearPointer + 1;
        } else if (frontPointer > rearPointer) {
            return arrayOfInt.length - frontPointer + (rearPointer + 1);
        } else {
            return 1;
        }
    }
}
