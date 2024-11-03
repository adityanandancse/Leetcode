# LEETCODE
# PROBLEM OF THE DAY SOLUTIONS

# 3/11/2024
# Question no. 796 Rotate String (https://leetcode.com/problems/rotate-string/)
# Solution in Java:

class Solution {

    public boolean rotateString(String s, String goal) {
    
        return s.length() == goal.length() && (s + s).contains(goal);
        
        
    }
    
}


# 2/11/2024
# Question no. 2490 Circular Sentence  (https://leetcode.com/problems/circular-sentence/)
# Solution in C++:

class Solution {

public:

    bool isCircularSentence(string sentence) {
    
        if(sentence.back()!=sentence[0])
        
        return false;
        
        int n=sentence.size();
        
        int i=0;
        
        while(i<n){
        
            while(i<n and sentence[i]!=' ')
            
            i+=1;
            
            if(i<n and sentence[i-1]!=sentence[i+1])
            
            return false;
            
            i+=1;
        }
        return true;
        
    }
    
};
