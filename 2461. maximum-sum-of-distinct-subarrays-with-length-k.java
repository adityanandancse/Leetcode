# 19/11/2024
# Question no. 2461 (https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/)
# Solution in JAVA

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        long winSum = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            winSum += nums[i];

            if (i >= k) {
                winSum -= nums[i - k];
                freq.put(nums[i - k], freq.get(nums[i - k]) - 1);
                if (freq.get(nums[i - k]) == 0) {
                    freq.remove(nums[i - k]);
                }
            }
            if (i >= k - 1 && freq.size() == k) {
                maxSum = Math.max(maxSum, winSum);
            }
        }
        return maxSum;
    }
}

