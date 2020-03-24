package com.fdobbrotv.data_structures.leetcode.queue.open_the_lock;

import java.util.*;
import java.util.stream.Collectors;

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

        List<Path> pathsOfDeadEnds = generatePossiblePaths(deadends);

        List<Path> pathsToTarget = generatePossiblePaths(target);

        List<Path> filteredPaths = pathsToTarget.stream()
                .filter(it -> !isPathComparableWithAnyOf(it, pathsOfDeadEnds))
                .collect(Collectors.toList());

        pathsOfDeadEnds.forEach(p -> isPathIncludes(pathsToTarget.get(0), p));

        result = getBestSolution(filteredPaths);

        return result;
    }

    private Byte getBestSolution(List<Path> filteredPaths) {
        return filteredPaths
                .stream()
                .map(it -> it.steps.size())
                .min(Integer::compare)
                .map(Integer::byteValue)
                .orElseGet(() -> (byte) -1);
    }

    private boolean isPathComparableWithAnyOf(Path targetPath, List<Path> pathsOfDeadEnds) {
        return pathsOfDeadEnds.stream().anyMatch(
                deadPath -> !isPathIncludes(targetPath, deadPath)
        );
    }

    private boolean isPathIncludes(Path left, Path right) {
        List<Step> stepsOfTarget = left.steps;
        List<Step> stepsOfDeadEnd = right.steps;
        int size = Math.min(stepsOfTarget.size(), stepsOfDeadEnd.size());
        boolean result = false;

        for (int i = 0; i < size; i++) {
            result = !stepsOfTarget.get(i).equals(stepsOfDeadEnd.get(i));
        }

        return result;
    }

    private List<Path> generatePossiblePaths(String... code) {
        List<Path> paths = new ArrayList<>();
        Arrays.stream(code).forEach(c -> paths.addAll(getPathsOfCode(c)));
        return paths;
    }

    private Collection<Path> getPathsOfCode(String code) {
        List<Path> paths = new ArrayList<>();

        byte[] targetBytes = toByteArray(code);

        paths.addAll(getShortestPath(targetBytes));
        paths.addAll(getBackwardPaths(targetBytes));
        paths.addAll(getForwardPaths(targetBytes));
        return paths;
    }

    private List<Path> getBackwardPaths(byte[] targetBytes) {
        List<Path> paths;
        Path path = new Path();

        byte backwardDistanceFirst = (byte) (10 - targetBytes[0]);
        while (backwardDistanceFirst-- > 0) {
            path.steps.add(Roll.FIRST_BACKWARD.getStep());
        }

        byte backwardDistanceSecond = (byte) (10 - targetBytes[1]);
        while (backwardDistanceSecond-- > 0) {
            path.steps.add(Roll.SECOND_BACKWARD.getStep());
        }

        byte backwardDistanceThird = (byte) (10 - targetBytes[2]);
        while (backwardDistanceThird-- > 0) {
            path.steps.add(Roll.THIRD_BACKWARD.getStep());
        }

        byte backwardDistanceFourth = (byte) (10 - targetBytes[3]);
        while (backwardDistanceFourth-- > 0) {
            path.steps.add(Roll.FOURTH_FORWARD.getStep());
        }

        paths = Collections.singletonList(path);
        return paths;
    }

    private List<Path> getForwardPaths(byte[] targetBytes) {
        List<Path> paths;
        Path path = new Path();

        byte forwardDistanceFirst = (byte) (state[0] + targetBytes[0]);
        while (forwardDistanceFirst-- > 0) {
            path.steps.add(Roll.FIRST_FORWARD.getStep());
        }

        byte forwardDistanceSecond = (byte) (state[1] + targetBytes[1]);
        while (forwardDistanceSecond-- > 0) {
            path.steps.add(Roll.SECOND_FORWARD.getStep());
        }

        byte forwardDistanceThird = (byte) (state[2] + targetBytes[2]);
        while (forwardDistanceThird-- > 0) {
            path.steps.add(Roll.THIRD_FORWARD.getStep());
        }

        byte forwardDistanceFourth = (byte) (state[3] + targetBytes[3]);
        while (forwardDistanceFourth-- > 0) {
            path.steps.add(Roll.FOURTH_FORWARD.getStep());
        }

        paths = Collections.singletonList(path);
        return paths;
    }

    private Collection<Path> getShortestPath(byte[] targetBytes) {
        List<Path> paths;

        Path path = new Path();

        byte forwardDistanceFirst = (byte) (state[0] + targetBytes[0]);
        byte backwardDistanceFirst = (byte) (10 - targetBytes[0]);
        if (forwardDistanceFirst <= backwardDistanceFirst) {
            while (forwardDistanceFirst-- > 0) {
                path.steps.add(Roll.FIRST_FORWARD.getStep());
            }
        } else {
            while (backwardDistanceFirst-- > 0) {
                path.steps.add(Roll.FIRST_FORWARD.getStep());
            }
        }

        byte forwardDistanceSecond = (byte) (state[1] + targetBytes[1]);
        byte backwardDistanceSecond = (byte) (10 - targetBytes[1]);
        if (forwardDistanceSecond <= backwardDistanceSecond) {
            while (forwardDistanceSecond-- > 0) {
                path.steps.add(Roll.SECOND_FORWARD.getStep());
            }
        } else {
            while (backwardDistanceSecond-- > 0) {
                path.steps.add(Roll.SECOND_BACKWARD.getStep());
            }
        }

        byte forwardDistanceThird = (byte) (state[2] + targetBytes[2]);
        byte backwardDistanceThird = (byte) (10 - targetBytes[2]);
        if (forwardDistanceThird <= backwardDistanceThird) {
            while (forwardDistanceThird-- > 0) {
                path.steps.add(Roll.THIRD_FORWARD.getStep());
            }
        } else {
            while (backwardDistanceThird-- > 0) {
                path.steps.add(Roll.THIRD_BACKWARD.getStep());
            }
        }

        byte forwardDistanceFourth = (byte) (state[3] + targetBytes[3]);
        byte backwardDistanceFourth = (byte) (10 - targetBytes[3]);
        if (forwardDistanceFourth <= backwardDistanceFourth) {
            while (forwardDistanceFourth-- > 0) {
                path.steps.add(Roll.FOURTH_FORWARD.getStep());
            }
        } else {
            while (backwardDistanceFourth-- > 0) {
                path.steps.add(Roll.FOURTH_BACKWARD.getStep());
            }
        }

        paths = Collections.singletonList(path);
        return paths;
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

    class Path {
        private List<Step> steps = new ArrayList<>();

        Path() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return steps.equals(path.steps);
        }

        @Override
        public int hashCode() {
            return Objects.hash(steps);
        }
    }

}

class Step {
    // 0 to 3
    private byte index;
    // 1 or -1
    private byte value;

    private Step() {
    }

    public Step(byte index, byte value) {
        //TODO: Allow only from 0 to 3
        this.index = index;
        //TODO: Allow only 1 and -1
        this.value = value;
    }

    public static Step of(int index, int value) {
        return new Step((byte) index, (byte) value);
    }

    public byte getIndex() {
        return index;
    }

    //TODO: Allow only from 0 to 3
    public void setIndex(byte index) {
        this.index = index;
    }

    public byte getValue() {
        return value;
    }

    //TODO: Allow only 1 and -1
    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return index == step.index &&
                value == step.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, value);
    }
}

enum Roll {
    FIRST_FORWARD(Step.of(0, 1)),
    FIRST_BACKWARD(Step.of(0, -1)),
    SECOND_FORWARD(Step.of(1, 1)),
    SECOND_BACKWARD(Step.of(1, -1)),
    THIRD_FORWARD(Step.of(2, 1)),
    THIRD_BACKWARD(Step.of(2, -1)),
    FOURTH_FORWARD(Step.of(3, 1)),
    FOURTH_BACKWARD(Step.of(3, -1));

    private Step step;

    Roll(Step step) {
        this.step = step;
    }

    public Step getStep() {
        return this.step;
    }
}