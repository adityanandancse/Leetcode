3/11/2024
Question no. 796 Rotate String (https://leetcode.com/problems/rotate-string/)

Solution in Java:

class Solution {
public boolean rotateString(String s, String goal) {
    return s.length() == goal.length() && (s + s).contains(goal);
}
}
