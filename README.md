# LEETCODE
# PROBLEM OF THE DAY SOLUTIONS

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

# 24/11/2024
# Question no. 1975 (https://leetcode.com/problems/maximum-matrix-sum/description/)
# Solution in Java

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        long sum = 0;
        int countNegatives = 0;
        int smallestAbsoluteValue = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.abs(matrix[i][j]);

                if (matrix[i][j] < 0) {
                    countNegatives++;
                }

                smallestAbsoluteValue = Math.min(smallestAbsoluteValue, Math.abs(matrix[i][j]));
            }
        }
        if (countNegatives % 2 == 0) {
            return sum;
        }
        return sum - 2L * smallestAbsoluteValue;
    }
}

# 23/11/2024
# Question no. 1861 (https://leetcode.com/problems/rotating-the-box/)
# Solution in java

class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] result = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = box[j][i];
            }
        }
        for (int i = 0; i < n; i++) {
            reverseRow(result[i]);
        }

        for (int j = 0; j < m; j++) {
            int spaceBottomRow = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                if (result[i][j] == '*') {
                    spaceBottomRow = i - 1;
                    continue;
                }
                    
                if (result[i][j] == '#') {
                    result[i][j] = '.';
                    result[spaceBottomRow][j] = '#';
                    spaceBottomRow--;
                }
            }
        }
        return result;
    }

    private void reverseRow(char[] row) {
        int left = 0, right = row.length - 1;
        while (left < right) {
            char temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left ++;
            right--;
        }
    }
}

# 22/11/2024
# Question no. 1072 (https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/description/)
# Solution in Java

class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix[0].length;
        int maxRows = 0;

        for (int[] currRow : matrix) {
            int[] inverted = new int[n];
            int count = 0;

            for (int col = 0; col < n; col++) {
                inverted[col] = currRow[col] == 0 ? 1 : 0;
            }

            for (int[] row : matrix) {
                if (Arrays.equals(row, currRow) || Arrays.equals(row, inverted)) {
                    count++;
                }
            }
            maxRows = Math.max(maxRows, count);
        }
        return maxRows;
    }
}


# 21/11/2024
# Question no. 2257 (https://leetcode.com/problems/count-unguarded-cells-in-the-grid/)
# Solution in Java
class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] matrix = new int[m][n];
        for (int[] guard : guards) {
            matrix[guard[0]][guard[1]] = 2;
        }
        for (int[] wall : walls) {
            matrix[wall[0]][wall[1]] = 3;
        }
        for (int r = 0; r < m; r++) {
            int state = 0;
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 2) {
                    state = 1;
                } else if (matrix[r][c] == 3) {
                    state = 0;
                } else {
                    matrix[r][c] = state | matrix[r][c];
                }
            }
            state = 0;
            for (int c = n - 1; c >= 0; c--) {
                if (matrix[r][c] == 2) {
                    state = 1;
                } else if (matrix[r][c] == 3) {
                    state = 0;
                } else {
                    matrix[r][c] = state | matrix[r][c];
                }
            }
        }
        for (int c = 0; c < n; c++) {
            int state = 0;
            for (int r = 0; r < m; r++) {
                if (matrix[r][c] == 2) {
                    state = 1;
                } else if (matrix[r][c] == 3) {
                    state = 0;
                } else {
                    matrix[r][c] = state | matrix[r][c];
                }
            }
            state = 0;
            for (int r = m - 1; r >= 0; r--) {
                if (matrix[r][c] == 2) {
                    state = 1; 
                } else if (matrix[r][c] == 3) {
                    state = 0;
                } else {
                    matrix[r][c] = state | matrix[r][c];
                }
            }
        }
        int res = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0){
                    res++;
                }
            }
        }
        return res;
    }
}


# 20/11/2024
# Question no. 2516 (https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/)
# Solution in JAVA

class Solution {

    public int takeCharacters(String s, int k) {
    
        int[] leftCounts = new int[3];
        
        int[] rightCounts = new int[3];
        
        int r = s.length();
        
        while (rightCounts[0] < k || rightCounts[1] < k || rightCounts[2] < k) {
        
            r--;
            
            if (r < 0)
            
            return -1;
            
            rightCounts[s.charAt(r) - 'a']++;
            
        }
        int res = s.length() - r;
        
        for (int l = 0; l < s.length(); l++) {
        
            int leftChar = s.charAt(l) - 'a';
            
            leftCounts[leftChar]++;
            
            while (r < s.length()) {
            
                int rightChar = s.charAt(r) - 'a';
                
                if (rightCounts[rightChar] + leftCounts[rightChar] > k) {
                
                    rightCounts[rightChar]--;
                    
                    r++;
                    
                } else {
                
                    break;
                    
                }
            }
            res = Math.min(res, l + 1 + s.length() - r);
            
        }
        return res;
        
    }
}


# 19/11/2024
# Question no. 2461 (https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/)
# Solution in JAVA

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        long winSum = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            winSum += nums[i];

            if (i >= k) {
                winSum -= nums[i - k];
                freq.put(nums[i - k], freq.get(nums[i - k]) - 1);
                if (freq.get(nums[i - k]) == 0) {
                    freq.remove(nums[i - k]);
                }
            }
            if (i >= k - 1 && freq.size() == k) {
                maxSum = Math.max(maxSum, winSum);
            }
        }
        return maxSum;
    }
}

# 18/11/2024
# Question no. 1652 (https://leetcode.com/problems/defuse-the-bomb/description/)
# Solution in C++

class Solution {
    void calculateSum(vector<int>& res, int k, vector<int> & prefix){
        int n = res.size();
        if (k > 0) {
            for (int i = 0; i < n; ++i)
            res[i] = prefix[i + k] - prefix[i];
        } else {
            k = abs(k);
            for (int i = n; i < 2 * n; ++i)
            res[i - n] = prefix[i - 1] - prefix[i - k - 1];
        }
    }
public:
    vector<int> decrypt(vector<int>& code, int k) {
        int n = code.size();
        if (k == 0)
        return vector <int>(n, 0);

        vector<int> prefix(2 * n);
        prefix[0] = code[0];
        for (int i = 1; i < 2 * n; ++i)
        prefix[i] = prefix[i - 1] + code[i % n];

        vector<int> res(n);
        calculateSum(res, k, prefix);


        return res;
    }
};


# 17/11/2024
# Question no. 862 (https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)
# Solution in Java

class Solution {
    public int shortestSubarray(int[] nums, int k) {
    
        ArrayDeque<long[]> window = new ArrayDeque<>();
        
        window.add(new long[] { -1, 0 });
        
        long res = Integer.MAX_VALUE;
        
        long total = 0;
        
        for (int i = 0; i < nums.length; i++) {
        
            if (nums[i] >= k) {
            
                return 1;
                
            }
            
            total += nums[i];
            
            while (!window.isEmpty() && window.getLast()[1] >= total) {
            
                window.removeLast();
                
            }
            window.add(new long[] { i, total});

            while (window.size() >= 2 && window.getLast()[1] - window.getFirst()[1] >= k) {
            
                res = Math.min(res, window.getLast()[0] - window.getFirst()[0]);
                
                window.removeFirst();
                
            }
        }
        return res == Integer.MAX_VALUE ? -1 : (int) res;
        
    }
}

# 16/11/2024
# Question no. 3254 (https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/description/)
# Solution in Java

class Solution {
    public int[] resultsArray(int[] nums, int k) {
    
            int[] res = new int[nums.length - k + 1];
            
            Arrays.fill(res, -1);
            
            int streak = 0;
            
            for (int i = 0; i < nums.length; i++) {
            
                if (i == 0 || nums[i] == nums[i - 1] + 1) {
                
                    streak++;
                    
                } else {
                
                    streak = 1;
                    
                }
                
                if (streak >= k) {
                
                    res[i - k + 1] = nums[i];
                    
                }
                
            }
            
            return res;
            
        }
    }

# 15/11/2024
# Question no. 1574 (https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/description/)
# Solution in JAVA

class Solution {
    private boolean canAssign( int mid, int n, int[] quantities) {
    
        int count = 0;
        
        for (int quantity : quantities) {
        
            count += Math.ceil((double) quantity/mid);
            
        }
        
        return count <= n;
        
    }
    public int minimizedMaximum(int n, int[] quantities) {
    
        int low = 1;
        
        int high = Arrays.stream(quantities).max().orElse(0);
        
        int ans = Integer.MAX_VALUE;
        

        while (low <= high) {
        
            int mid = low + (high - low) / 2;
            
            if (canAssign(mid, n, quantities)) {
            
                ans = Math.min(ans, mid);
                
                high =  mid - 1;
                
            } else {
            
                low = mid + 1;
                
            }
            
        }
        
        return ans;
        
    }
}

# 14/11/2024
# Question no. 2064 (https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/description/)
# Solution in JAVA

class Solution {
    private boolean canAssign( int mid, int n, int[] quantities) {
    
        int count = 0;
        
        for (int quantity : quantities) {
        
            count += Math.ceil((double) quantity/mid);
            
        }
        
        return count <= n;
        
    }
    
    public int minimizedMaximum(int n, int[] quantities) {
    
        int low = 1;
        
        int high = Arrays.stream(quantities).max().orElse(0);
        
        int ans = Integer.MAX_VALUE;

        while (low <= high) {
        
            int mid = low + (high - low) / 2;
            
            if (canAssign(mid, n, quantities)) {
            
                ans = Math.min(ans, mid);
                
                high =  mid - 1;
                
            } else {
            
                low = mid + 1;
                
            }
            
        }
        
        return ans;
        
    }
}

# 13/11/2024
# Question no. 2563 (https://leetcode.com/problems/count-the-number-of-fair-pairs/description/)
# Solution in C++

class Solution {

public:

    long long countFairPairs(vector<int>& nums, int lower, int upper) {
    
        int n = nums.size();
        
        sort(begin(nums), end(nums));
        
        long long result = 0;
        

        for(int i = 0; i < n; i++) {
        
            int idx = lower_bound(begin(nums) + i + 1, end(nums), lower-nums[i]) - begin(nums);
            
            int x = idx - 1 - i;
            
            idx = upper_bound(begin(nums) + i + 1, end(nums), upper-nums[i]) - begin(nums);
            
            int y = idx - 1 - i;
            
            result += (y-x);
            
        }
        
        return result;
        
    }
    
};

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


# 11/11/2024
# Question no. 2601 (https://leetcode.com/problems/prime-subtraction-operation/description/)
# Solution in C++
class Solution {
    public:
    bool isPrime[1000];

    void sieve() {
        fill(isPrime, isPrime+1000, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i * i < 1000; i++) {
            if(isPrime[i] == true) {
                for(int j = i*i; j < 1000; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    bool primeSubOperation(vector<int>& nums) {
        int n = nums.size();

        sieve();

        for(int i = n-2; i >= 0; i--) {
            if(nums[i] < nums[i+1]){
                continue;
            }
        

        for(int p = 2; p < nums[i]; p++) {
            if(!isPrime[p]) {
                continue;
            }

            if(nums[i] - p < nums[i+1]) {
                nums[i] -= p;
                break;
            }
        }
    if(nums[i] >= nums[i+1]) {
        return false;
    }
}
    return true;
    }
};

# 10/11/2024
# Question no. 3097 (https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii)
# Solution i C++

class Solution {

public:

    void update(int number, vector<int>& vec, int val){
    
        for (int i = 0; i < 32; i++) {
        
            if((number >> i) & 1){
            
                vec[i] += val;
                
            }
            
        }
        
    }
    
    int getDecimalFromBinary(vector<int>& vec) {
    
        int num = 0;
        

        for(int i = 0; i < 32; i++) {
        
            if (vec[i] > 0) {
            
                num |= (1 << i);
            }
            
        }
        return num;
    }

    int minimumSubarrayLength(vector<int>& nums, int k) {
        int n = nums.size();

        int result = INT_MAX;

        int i = 0; 
        int j = 0;

        vector <int> vec(32, 0);

        while(j < n) {
            update(nums[j], vec, 1);

            while(i <= j && getDecimalFromBinary(vec) >= k) {
                result = min(result, j-i+1);
                update(nums[i], vec, -1);
                i++;
            }
            j++;
        }
        return result == INT_MAX ? -1 : result;
    }
};

# 9/11/2024
# Question no. 3133 Minimum Array End (https://leetcode.com/problems/minimum-array-end/)
# Solution in Java

class Solution {
    public long minEnd(int n, int x) {
        int i = 0;
        long m = n;
        long res = x;
        m--;

        while (m > 0) {
            while (((1L << i) & res) != 0) {
                i++;
            }
            res |= ((m & 1) << i);
            i++;
            m >>= 1;
        }
        return res;
    }
}


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
