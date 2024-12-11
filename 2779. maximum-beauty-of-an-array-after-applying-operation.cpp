// 11/12/2024
//  ( https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/ )

class Solution {
public:
    int maximumBeauty(vector<int>& nums, int k) {
        sort(begin(nums), end(nums));
        int maxBeauty = 0;
        for (int i = 0; i < nums.size(); i++) {
            auto upperBound = upper_bound(nums.begin(), nums.end(), nums[i] + 2 * k);
            maxBeauty = max(maxBeauty, int(upperBound - nums.begin()) - i);
        }
        return maxBeauty;
    }
};
