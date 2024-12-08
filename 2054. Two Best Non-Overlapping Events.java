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
