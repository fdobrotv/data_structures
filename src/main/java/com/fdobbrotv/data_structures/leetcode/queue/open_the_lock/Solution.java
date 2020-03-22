package com.fdobbrotv.data_structures.leetcode.queue.open_the_lock;

import java.util.Arrays;

public class Solution {
    byte[] state;

    public int openLock(String[] deadends, String target) {
        resetLockState();
        byte[] targetBytes = toByteArray(target);

        byte result = -1;

        boolean isDeadendsContainsTarget = Arrays.asList(deadends).contains(target);
        boolean isDeadEndsContainsInitValue = Arrays.asList(deadends).contains("0000");
        if (isDeadendsContainsTarget || isDeadEndsContainsInitValue) {
            return result;
        }

        if (Arrays.equals(state, targetBytes)) {
            result = 0;
            return result;
        }

        result = simpleWayStepsCounter(targetBytes);

        return result;
    }

    private byte simpleWayStepsCounter(byte[] targetBytes) {
        byte result = 0;

        byte forwardDistanceFirst = (byte) (state[0] + targetBytes[0]);
        byte forwardDistanceSecond = (byte) (state[1] + targetBytes[1]);
        byte forwardDistanceThird = (byte) (state[2] + targetBytes[2]);
        byte forwardDistanceFourth = (byte) (state[3] + targetBytes[3]);

        byte backwardDistanceFirst = (byte) (10 - targetBytes[0]);
        byte backwardDistanceSecond = (byte) (10 - targetBytes[1]);
        byte backwardDistanceThird = (byte) (10 - targetBytes[2]);
        byte backwardDistanceFourth = (byte) (10 - targetBytes[3]);

        if (forwardDistanceFirst <= backwardDistanceFirst) {
            while (forwardDistanceFirst-- > 0) {
                state[0] = ++state[0];
                ++result;
            }
        } else {
            while (backwardDistanceFirst-- > 0) {
                state[0] = --state[0];
                ++result;
            }
        }

        if (forwardDistanceSecond <= backwardDistanceSecond) {
            while (forwardDistanceSecond-- > 0) {
                state[1] = ++state[1];
                ++result;
            }
        } else {
            while (backwardDistanceSecond-- > 0) {
                state[1] = --state[1];
                ++result;
            }
        }

        if (forwardDistanceThird <= backwardDistanceThird) {
            while (forwardDistanceThird-- > 0) {
                state[2] = ++state[2];
                ++result;
            }
        } else {
            while (backwardDistanceThird-- > 0) {
                state[2] = --state[2];
                ++result;
            }
        }

        if (forwardDistanceFourth <= backwardDistanceFourth) {
            while (forwardDistanceFourth-- > 0) {
                state[3] = ++state[3];
                ++result;
            }
        } else {
            while (backwardDistanceFourth-- > 0) {
                state[3] = --state[3];
                ++result;
            }
        }
        return result;
    }

    private byte[] toByteArray(String target) {
        char[] targetStateChars = target.toCharArray();
        byte[] result = new byte[4];
        byte index = 0;
        for (char wheelValue : targetStateChars) {
            result[index++] = Byte.parseByte(String.valueOf(wheelValue));
        }
        return result;
    }

    private void resetLockState() {
        state = new byte[]{0, 0, 0, 0};
    }
}