# 8/11/2024
# Question no. 1829 (https://leetcode.com/problems/maximum-xor-for-each-query/description/)
# Solution in Java

class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) { 
        int xor = 0;      
        for (int num : nums) {       
            xor ^= num;         
        }       
        int biggestNum = (int) Math.pow(2,maximumBit) -1;        
        int[] res = new int  [nums.length];        
        for (int i = 0; i<nums.length; i++) {        
            res [i] = xor ^ biggestNum;        
            xor ^= nums[nums.length -i -1];            
        }        
        return res;   
    }   
}
