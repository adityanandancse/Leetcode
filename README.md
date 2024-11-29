# LEETCODE
# PROBLEM OF THE DAY SOLUTIONS

# 28/11/2024
# Question no. 2290 (https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/description/)
# Solution in JAVA

class Solution {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] result = new int[m][n];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        result[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0});

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int obstacleCount = current[0];
            int i = current[1];
            int j = current[2];

            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];

                if (x >= 0 && x < m && y >= 0 && y < n) {
                    int wt = (grid[x][y] == 1) ? 1 : 0;

                    if (obstacleCount + wt < result[x][y]) {
                        result[x][y] = obstacleCount + wt;
                        pq.offer(new int[]{obstacleCount + wt, x, y});
                    }
                }
            }
        }
        return result[m - 1][n - 1];
    }
}

# 27/11/2024
# Question no. 3243 (https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/description/)
# Solution in JAVA

class Solution {
    private Map<Integer, List<int[]>> adj = new HashMap<>();

    private int dijkstra(int n) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0];
            int node = top[1];

            if (node == n - 1) {
                return result[n - 1];
            }
            if (d > result[node]) {
                continue;
            }
            if (!adj.containsKey(node)) {
                continue;
            }
            for (int[] edge : adj.get(node)) {
                int adjNode = edge[0];
                int dist = edge[1];
                if (d + dist < result[adjNode]) {
                    result[adjNode] = d + dist;
                    pq.offer(new int[]{d + dist, adjNode});
                }
            }
        }
        return result[n - 1];
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        for (int i  = 0; i < n - 1; ++i) {
            adj.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{i + 1, 1});
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++){
            int u = queries[i][0];
            int v = queries[i][1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, 1});
            result[i] = dijkstra(n);
        }
        return result;
    }
}


# 26/11/2024
# Question no. 2924 (https://leetcode.com/problems/find-champion-ii/description/)
# Solution in Java

class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            indegree[v]++;
        }
        int champ = -1;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                champ = i;
                count++;
                if (count > 1) {
                    return -1;
                }
            }
        }
        return champ;
    }
}

# 25/11/2024
# Question no. 773 (https://leetcode.com/problems/sliding-puzzle/description/)
# Solution in Java

class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                start.append(board[i][j]);
            }
        }
        String target = "123450";

        Map<Integer, int[]> mp = new HashMap<>();
        mp.put(0, new int[]{1, 3});
        mp.put(1, new int[]{0, 2, 4});
        mp.put(2, new int[]{1, 5});
        mp.put(3, new int[]{0, 4});
        mp.put(4, new int[]{1, 3, 5});
        mp.put(5, new int[]{2, 4});

        Queue<String> queue = new LinkedList<>();
        queue.offer(start.toString());

        Set<String> visited = new HashSet<>();
        visited.add(start.toString());

        int level = 0;

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                String curr = queue.poll();

                if (curr.equals(target)) {
                    return level;
                }

                int indexOfZero = curr.indexOf('0');
                for (int swapIdx : mp.get(indexOfZero)) {
                    char[] newStateArray = curr.toCharArray();

                    char temp = newStateArray[indexOfZero];
                    newStateArray[indexOfZero] = newStateArray[swapIdx];
                    newStateArray[swapIdx] = temp;

                    String newState = new String(newStateArray);

                    if (!visited.contains(newState)) {
                        queue.offer(newState);
                        visited.add(newState);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}

