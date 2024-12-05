// 05/12/2024
//   (https://leetcode.com/problems/move-pieces-to-obtain-a-string/)

class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        Map<String, Boolean>memo = new HashMap<>();
        return solve(start, target, n, memo);
    }
    private boolean solve(String start, String target, int n, Map<String, Boolean> memo) {
        if (start.equals(target)) {
            return true;
        }
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        for (int i = 0; i < n; i++) {
            if (start.charAt(i) == 'L' && i > 0 && start.charAt (i - 1) == '_') {
                String swapped = swap(start, i, i - 1);
                if (solve(swapped, target, n, memo)) {
                    return true;
                }
            } else if (start.charAt(i) == 'R' && i < n - 1 && start.charAt(i + 1) == '_') {
                String swapped = swap(start, i, i + 1);
                if (solve(swapped, target, n, memo)) {
                    return true;
                }
            }
        }
        memo.put(start, false);
        return false;
    }
    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars [i] = chars[j];
        chars [j] = temp;
        return new String(chars);
    }
}
