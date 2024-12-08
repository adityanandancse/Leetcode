// 08/12/2024
//   (https://leetcode.com/problems/two-best-non-overlapping-events/description/)

class Solution {
    public int maxTwoEvents(int[][] events) {
      List<int[]> lineSweep = new ArrayList<>();
      for (int[] event : events) {
        lineSweep.add(new int[]{event[0], 1, event[2]});
        lineSweep.add(new int[]{event[1] + 1, -1, event[2]});
      }  
      lineSweep.sort((a, b) -> a[0] - b[0]);

      int maxVal = 0, maxSeen = 0;
      for (int[] event : lineSweep) {
        int point = event[0], status = event[1], val = event[2];
        if (status == 1) {
            maxVal = Math.max(maxVal, maxSeen + val);
        } else {
            maxSeen = Math.max(maxSeen, val);
        }
      }
      return maxVal;
    }
}



class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        vector<vector<int>> line_sweep;
        for(auto& event: events){
            int start = event[0];
            int end = event[1];
            int val = event[2];
            line_sweep.push_back({start,1,val});
            line_sweep.push_back({end+1,-1,val});
        }
        sort(line_sweep.begin(),line_sweep.end());

        int max_val=0;
        int max_seen=0;
        for(auto& event: line_sweep){
            int point = event[0];
            int status = event[1];
            int val = event[2];

            if(status==1)
            max_val = max(max_val,max_seen+val);
            if(status==-1)
            max_seen = max(max_seen, val);
        }
        return max_val;
    }
};
