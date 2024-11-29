9/11/2024
Question no. 3133 Minimum Array End (https://leetcode.com/problems/minimum-array-end/)
Solution in Java

class Solution {
    public long minEnd(int n, int x) {
        int i = 0;
        long m = n;
        long res = x;
        m--;

        while (m > 0) {
            while (((1L << i) & res) != 0) {
                i++;
            }
            res |= ((m & 1) << i);
            i++;
            m >>= 1;
        }
        return res;
    }
}
