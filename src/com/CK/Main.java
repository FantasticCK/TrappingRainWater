package com.CK;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
//        int[] height = new int[]{2, 0, 2};
//        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = new int[]{5, 4, 1, 2};
        int[] height = new int[]{5, 2, 1, 2, 1, 5};
        Solution solution = new Solution();
        System.out.println(solution.trap(height));
    }
}

class Solution {
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int left, right, leftH, rightH, trap = 0, shadow = 0;
        int[] highest;
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < height.length; i++) {
            heap.add(new int[]{i, height[i]});
        }

        highest = heap.poll();
        left = highest[0];
        right = highest[0];
        leftH = highest[1];
        rightH = highest[1];
        while (!heap.isEmpty()) {
            highest = heap.poll();
            if (highest[0] < left) {
                int tempRight = left;
                int tempRightH = leftH;
                left = highest[0];
                leftH = height[left];
                for (int j = left; j <= tempRight; j++) {
                    if (height[j] < Math.min(leftH, tempRightH)) shadow += height[j];
                    else shadow += Math.min(leftH, tempRightH);
                }
                trap += (Math.min(leftH, tempRightH) * (tempRight - left + 1) - shadow);
                shadow = 0;
            }
            if (highest[0] > right) {
                int tempLeft = right;
                int tempLeftH = rightH;
                right = highest[0];
                rightH = height[right];
                for (int j = tempLeft; j <= right; j++) {
                    if (height[j] < Math.min(tempLeftH, rightH)) shadow += height[j];
                    else shadow += Math.min(tempLeftH, rightH);
                }
                trap += (Math.min(tempLeftH, rightH) * (right - tempLeft + 1)) - shadow;
                shadow = 0;
            }
        }
        return trap;
    }
}

// Two Pointers
class Solution2 {
    public int trap(int[] A) {
        if (A.length < 3) return 0;

        int ans = 0;
        int l = 0, r = A.length - 1;

        // find the left and right edge which can hold water
        while (l < r && A[l] <= A[l + 1]) l++;
        while (l < r && A[r] <= A[r - 1]) r--;

        while (l < r) {
            int left = A[l];
            int right = A[r];
            if (left <= right) {
                // add volum until an edge larger than the left edge
                while (l < r && left >= A[++l]) {
                    ans += left - A[l];
                }
            } else {
                // add volum until an edge larger than the right volum
                while (l < r && A[--r] <= right) {
                    ans += right - A[r];
                }
            }
        }
        return ans;
    }
}