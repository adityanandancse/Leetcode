5/11/2024
Question no. 2914 Minimum no. of changes to make binary string beautiful (https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/description/)

Solution in Java

class Solution {
    public int minChanges(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i += 2){
            if (s.charAt(i) != s.charAt(i + 1)){
                res++;  
            }
        }
        return res;
    }
}
