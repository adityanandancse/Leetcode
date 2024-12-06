// 06/12/2024
//   Question no. 2554 (https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/)

class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> bannedSet = new HashSet<>();
        for (int num : banned) {
            bannedSet.add(num);
        }
        int count = 0, sum = 0;
        for (int i = 1; i <= n; i++) {
            if (!bannedSet.contains(i)) {
                sum += i;
                if (sum > maxSum) {
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
