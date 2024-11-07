# LEETCODE
# PROBLEM OF THE DAY SOLUTIONS

# 7/11/2024
# Questtion no. 2275 (https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/)
# Solution in java

class Solution {
    public int largestCombination(int[] candidates) {
        int [] bitCounts = new int[32];
        for (int num : candidates) {
            int i = 0;
            while (num != 0){
            bitCounts[i] += num & 1;
            num >>= 1;
            i++;
        }
    }
    int res = 0;
    for (int num : bitCounts) {
        res = Math.max(res, num);
    }
    return res;
}
}


# 6/11/2024
# Question no. 3011 Find if array can be sorted (https://leetcode.com/problems/find-if-array-can-be-sorted/description/)
# Solution in C++

class Solution {

public:

    bool canSortArray(vector<int>& nums) {
    
        int n = nums.size();
        
        int prev_segment_max=INT_MIN;
        
        int curr_segment_max = nums[0];
        
        int curr_segment_min = nums[0];
        
        int set_bit_count = __builtin_popcount(nums[0]);
        
        int i = 1;
        
        while(i<n){
        
            while(i<n and __builtin_popcount(nums[i])==set_bit_count){
            
                curr_segment_max = max(curr_segment_max, nums[i]);
                
                curr_segment_min = min(curr_segment_min, nums[i]);
                
                i++;  
            }
            
            if(prev_segment_max > curr_segment_min){
            
                return false;
                
            }
                else if(i<n){
                
                    prev_segment_max = curr_segment_max;
                    
                    curr_segment_max = nums[i];
                    
                    curr_segment_min = nums[i];
                    
                    set_bit_count = __builtin_popcount(nums[i]);
                    
                }
                
        }
        
            return true;
            
    }
    
};


# 5/11/2024
# Question no. 2914 Minimum no. of changes to make binary string beautiful (https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/description/)
# Solution in Java

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


# 4/11/2024
# Question no. 3163 String Compression III (https://leetcode.com/problems/string-compression-iii/)
# Solution in C++:

class Solution {
public:
    string compressedString(string word) {
        int n = word.size();
        string res = "";
    
    int i = 1;
    int start = 0;
    while (i<n) {
        while(i<n and word[i]==word[i-1] and (i-start)<10)
        i++;

        if((i-start)==10) i--;

        res.append(to_string(i-start));
        res.push_back(word[start]);

        start = i;
        i++;
    }
    if (start == n-1){
        res.append(to_string(i-start));
        res.push_back(word[start]);
    }
    return res;
}
};

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
