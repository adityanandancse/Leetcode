6/11/2024
Question no. 3011 Find if array can be sorted (https://leetcode.com/problems/find-if-array-can-be-sorted/description/)
Solution in C++

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
