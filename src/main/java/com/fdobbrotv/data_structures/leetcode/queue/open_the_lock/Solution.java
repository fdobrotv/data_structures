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

        pathsOfDeadEnds.forEach(p -> {
            isPathIncludes(pathsToTarget.get(0), p);
        });

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
        List<Path> paths;

        byte[] targetBytes = toByteArray(code);

        Path path = new Path();

        byte forwardDistanceFirst = (byte) (state[0] + targetBytes[0]);
        byte backwardDistanceFirst = (byte) (10 - targetBytes[0]);
        if (forwardDistanceFirst <= backwardDistanceFirst) {
            while (forwardDistanceFirst-- > 0) {
                Step step = new Step();
                step.index = 0;
                step.value = 1;
                path.steps.add(step);
            }
        } else {
            while (backwardDistanceFirst-- > 0) {
                Step step = new Step();
                step.index = 0;
                step.value = -1;
                path.steps.add(step);
            }
        }

        byte forwardDistanceSecond = (byte) (state[1] + targetBytes[1]);
        byte backwardDistanceSecond = (byte) (10 - targetBytes[1]);
        if (forwardDistanceSecond <= backwardDistanceSecond) {
            while (forwardDistanceSecond-- > 0) {
                Step step = new Step();
                step.index = 1;
                step.value = 1;
                path.steps.add(step);
            }
        } else {
            while (backwardDistanceSecond-- > 0) {
                Step step = new Step();
                step.index = 1;
                step.value = -1;
                path.steps.add(step);
            }
        }

        byte forwardDistanceThird = (byte) (state[2] + targetBytes[2]);
        byte backwardDistanceThird = (byte) (10 - targetBytes[2]);
        if (forwardDistanceThird <= backwardDistanceThird) {
            while (forwardDistanceThird-- > 0) {
                Step step = new Step();
                step.index = 2;
                step.value = 1;
                path.steps.add(step);
            }
        } else {
            while (backwardDistanceThird-- > 0) {
                Step step = new Step();
                step.index = 2;
                step.value = -1;
                path.steps.add(step);
            }
        }

        byte forwardDistanceFourth = (byte) (state[3] + targetBytes[3]);
        byte backwardDistanceFourth = (byte) (10 - targetBytes[3]);
        if (forwardDistanceFourth <= backwardDistanceFourth) {
            while (forwardDistanceFourth-- > 0) {
                Step step = new Step();
                step.index = 3;
                step.value = 1;
                path.steps.add(step);
            }
        } else {
            while (backwardDistanceFourth-- > 0) {
                Step step = new Step();
                step.index = 3;
                step.value = -1;
                path.steps.add(step);
            }
        }

        paths = Collections.singletonList(path);
        return paths;
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

    class Path {
        private List<Step> steps = new ArrayList<>();

        Path() {
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
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
}