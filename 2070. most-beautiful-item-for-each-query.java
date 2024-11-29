# 12/11/2024
# Question no. 2070 (https://leetcode.com/problems/most-beautiful-item-for-each-query/description/)
# Solution in Java

class Solution {

    public int[] maximumBeauty(int[][] items, int[] queries) {
    
        Arrays.sort(items, (item1, item2) -> item1[0] - item2[0]);
        
        for (int i = 1; i < items.length; ++i) {
        
            items[i][1] = Math.max(items[i - 1][1], items[i][1]);
            
        }
        
            int queryCount = queries.length;
            
            int[] answers = new int[queryCount];
            
            for (int j = 0; j < queryCount; ++j) {
            
                int left = 0, right = items.length;
                
                while (left < right) {
                
                    int mid = (left + right) >> 1;
                    
                    if (items[mid][0] > queries[j]) {                 
                        right = mid;                      
                    }                 
                    else{                
                        left = mid + 1;                     
                    }                  
                }              
                if (left > 0) {               
                    answers[j] = items[left - 1][1];                   
                }                
                else{                
                    answers[j] = 0;                    
                }   
            }
            return answers;        
        }  
    }


