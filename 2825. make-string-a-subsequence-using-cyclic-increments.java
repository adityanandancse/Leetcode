// 4/12/2024
//   Question no. 2825 (https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/)
//                      Solution in java:

class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        if (n > m) return false;

        int first = -1, second = -1;
        while (first < m && second < n) {
            first++;
            second++;

            while (first < m) {
                char nextChar = (char) (((str1.charAt(first) - 'a' + 1) % 26) + 'a');
                if (str2.charAt(second) == str1.charAt(first) || str2.charAt(second) == nextChar) {
                    break;
                }
                first++;
            }
        }
        if (first == m && second < n)
        return false;
        return true;
    }
}

    
