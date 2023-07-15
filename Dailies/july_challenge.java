import java.util.*;

public class july_challenge {

    //  1 July 2023
    //  Fair distribution of cookies
    public int distributeCookies(int[] cookies, int k) {
        int[] minUnfairness = {Integer.MAX_VALUE};
        int[] allocation = new int[k];
        Arrays.fill(allocation,0);
        Arrays.sort(cookies); // Maybe sorting it makes it faster? Honestly idk

        backTracking(cookies, minUnfairness, k, allocation, 0);
        return minUnfairness[0];
    }

    private void backTracking(int[] cookies, int[] curMin, int k, int[] allocation, int index){
        
        //  All cookies have been allocated
        if (index == cookies.length){
            int curMax = Arrays.stream(allocation).max().orElse(Integer.MAX_VALUE);
            if (curMax < curMin[0]){
                curMin[0] = curMax;
            }
            return;
        }

        for (int i = 0; i < k; i++) {
            allocation[i] += cookies[index];
            
        //  Only continue if the allocation has less cookies than the current minimum
            if (allocation[i] <= curMin[0]){
               backTracking(cookies, curMin, k, allocation, index+1); 
            }
            allocation[i] -= cookies[index];
        }
    }

    //  2 July 2023
    //  Maximum number of achievable transfer requests
    public int maximumRequests(int n, int[][] requests) {

        //  Array to see net change in numbers for each building
        int[] allocation = new int[n];
        Arrays.fill(allocation, 0);

        //  HashSet to keep track of how many requests are processed
        HashSet<Integer> processed = new HashSet<>();

        int curMax[] = {0};
        backtrackRequest(requests, processed, curMax, allocation, 0);
        return curMax[0];
    }

    private void backtrackRequest(int[][] requests, HashSet<Integer> processed, int[] curMax, int[]allocation, int index){

        //  Reached the end of requests
        if (index == requests.length){
            for (int i = 0; i < allocation.length; i++) {
                if (allocation[i] != 0){
                    return;
                }
            }
            if (curMax[0] < processed.size()){
                curMax[0] = processed.size();
            }
            return;
        }

        //  Allow current allocation if net change for all buildings is 0
        boolean canAllocate = true;
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] != 0){
                canAllocate = false;
                break;
            }
        }
        if (canAllocate){
            if (curMax[0] < processed.size()){
                curMax[0] = processed.size();
            }
        }

        for (int i = index; i < requests.length; i++) {
            allocation[requests[i][0]]--;
            allocation[requests[i][1]]++;
            processed.add(i);
            backtrackRequest(requests, processed, curMax, allocation, i+1);
            allocation[requests[i][0]]++;
            allocation[requests[i][1]]--;
            processed.remove(i);
        }
    }

    //  3 July 2023
    //  Buddy strings
    public boolean buddyStrings(String s, String goal) {

        //  Strings have different length
        if (s.length()!=goal.length()){
            return false;
        }

        //  Keep track of index of characters that are different
        int char1, char2;
        char1 = char2 = -1;

        //  Keep track of number of each letter in s
        Set<Character> letterCount = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)){
                if (char1 == -1){
                    char1 = i;
                }
                else if (char2 == -1){
                    char2 = i;
                }
                else{
                    return false;
                }
            }
            letterCount.add(s.charAt(i));
        }

        //  Only one character is different, means no swaps can achieve desired state
        if (char1 != -1 && char2 == -1){
            return false;
        }

        //  s = goal
        if (char1 == -1 && char2 == -1){
            if (s.length() != letterCount.size()){
                return true;
            }
            return false;
        }

        //  2 characters are different
        if (s.charAt(char1) == goal.charAt(char2) && s.charAt(char2) == goal.charAt(char1)){
            return true;
        }

        return false;
    }

    //  4 July 2023
    //  Single number 2
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
        
            //  For each num, by shifting it i bits to the right, we effectively count the total number of 1s at that bit
            //  Since most numbers appears exactly 3 times expect one, applying modulo of 3 can single out that number
            for (int num : nums){
                sum += num >> i & 1;
            }
            sum %= 3;
            ans |= sum << i;
        }
        return ans;
    }

    //  5 July 2023
    //  Longest Subarray of 1's After Deleting One Element
    public int longestSubarray(int[] nums) {
        //  Use of sliding window to update curMax

        int start, end, curMax, zeroCount;
        start = end = curMax = zeroCount = 0;

        //  Iterate through nums array
        while (end < nums.length){
            if (nums[end] == 1 && zeroCount <= 1){
                end++; // Expand the sliding window
            }
            else{
                if (zeroCount == 0){
                    zeroCount++;
                    end++;
                }
                else{
                    //  Update max sliding window
                    curMax = Math.max(curMax, (end - start));

                    //  Update start index until a zero is reached
                    while (nums[start] != 0){
                        start++;
                    }
                    start++;
                    zeroCount--;
                }
            }
        }
        return Math.max(curMax-1, end-start-1);
    }

    //  6 July 2023
    //  Minimum size subarray sum
    public int minSubArrayLen(int target, int[] nums) {
        int start, end, curSum, minCount, curCount;
        start = end = curSum = curCount = 0;
        minCount = Integer.MAX_VALUE;

        while (end < nums.length){
            if (curSum < target){
                curSum += nums[end++];
                curCount++;
            }
            else{
                minCount = Math.min(minCount, curCount);
                curSum -= nums[start++];
                curCount--;
            }
        }

        //  Decrease sliding window if sum > target
        while (curSum > target){
            curSum -= nums[start++];
            curCount--;
        }
        
        if (curSum >= target){
            return Math.min(minCount, curCount);
        }
        //  Sum less than target and whole array is traversed i.e. no answer
        else if (curCount == nums.length){
            return 0;
        }
        //  Sum less than target, so we add 1 and return the min between that and the current minimum
        else{
            return Math.min(minCount, curCount+1);

        }
    }  

    //  7 July 2023
    //  Maximize the confusion of an exam
    public int maxConsecutiveAnswers(String answerKey, int k) {
        //  Sliding window while keeping track of number of true and false

        int start, end, trueCount, falseCount, maxConsecutives;
        start = end = trueCount = falseCount = maxConsecutives = 0;

        char[] answerKeyArray = answerKey.toCharArray();
        while (end < answerKeyArray.length){
            if (trueCount <= k || falseCount <= k){
                if (answerKeyArray[end] == 'T'){
                    trueCount++;
                }
                else{
                    falseCount++;
                }
                end++;
            }
            else{
                maxConsecutives = Math.max(maxConsecutives, end - start - 1);
                while (trueCount > k && falseCount > k && start < answerKeyArray.length){
                    if (answerKeyArray[start] == 'T'){
                        trueCount--;
                    }
                    else{
                        falseCount--;
                    }
                    start++;
                }
            }
        }

        if (trueCount > k && falseCount > k){
            //  e.g. TFFT and k = 1, at the second T, it is still included because at that instance trueCount = 1
            return Math.max(maxConsecutives, end - start - 1);
        }
        return Math.max(maxConsecutives, end - start);
    }

    //  8 July 2023
    //  Put marbles in bags
    public long putMarbles(int[] weights, int k) {
        //  Assume if i partition at weights[i], the partition is between i and i+1
        //  e.g  2 3 4 5. and partition at 2 will give 2 | 3 4 5
        //  If we partition at index i, this will increase the sum by weights[i] + weights[i+1]

        int len = weights.length;

        if (len == k){
            return 0;
        }

        int[] sumIncrease = new int[len-1];

        for (int i = 0; i < len-1; i++) {
            sumIncrease[i] = weights[i] + weights[i+1];
        }
        
        Arrays.sort(sumIncrease);

        long minSum = 0;
        long maxSum = 0;

        for (int i = 0; i < k-1; i++) {
            minSum += sumIncrease[i];
            maxSum += sumIncrease[len-2-i];
        }
        
        return maxSum - minSum;

    }

    //  9 July 2023
    //  Substring with largest variance
    public int largestVariance(String s) {
        // Kadane's algorithm
        // I'm sorry this solution is mostly copied from online as I have non idea how to improve time complexity from 26^2 * N^2
        //  key considerations: a substring with only one kind of letter has variance 0, e.g. aaaa

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[(int)(s.charAt(i) - 'a')]++;
        }

        int maxVariance = 0;
        
        for (int a = 0; a < 26; a++) {
            for (int b = 0; b < 26; b++) {
                int remainingA = freq[a];
                int remainingB = freq[b];
                if (a == b || remainingA == 0 || remainingB == 0){
                    continue;
                }

                int curBfreq = 0;
                int curAfreq = 0;

                //  We assume for each letter pair (a,b), b needs to be appear more than a in order for maxVariance to get updated
                //  The opposite pair (b,a) will still get considered since the two for loops both runs from 0 through 25
                for (int i = 0; i < s.length(); i++) {
                    int c = (int)(s.charAt(i) - 'a');
                    if (c==b){
                        curBfreq++;
                    }
                    if (c==a){
                        curAfreq++;
                        remainingA--;
                    }
                    //  letter a needs to appear at least once or else it won't be a valid substring
                    if (curAfreq > 0){
                        maxVariance = Math.max(maxVariance, curBfreq -  curAfreq);
                    }

                    //  We only reset counter (i.e. finished considering substring up till current index) if b appears more than a, which gives negative variance
                    //  AND if there's still more letter a remaining, since that's required for a valid substring
                    if (curBfreq < curAfreq && remainingA > 0){
                        curBfreq = 0;
                        curAfreq = 0;
                    }
                    
                }
            }
        }
        return maxVariance;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
        }
    }

    //  10 July 2023
    //  Minimum depth of binary tree
    public int minDepth(TreeNode root) {
    //  BFS to search for first leaf node
    //  Traverses each level at a time, and returns the first leaf node's depth

        if (root == null){
            return 0;
        }
        
        Queue<nodeQueue> queue = new LinkedList<>();
        queue.add(new nodeQueue(root, 1));
        while (!queue.isEmpty()){
            nodeQueue curNode = queue.poll();
            if (curNode.node.left == null && curNode.node.right == null){
                return curNode.depth;
            }
            if (curNode.node.left != null){
                queue.add(new nodeQueue(curNode.node.left, curNode.depth+1));
            }
            if (curNode.node.right != null){
                queue.add(new nodeQueue(curNode.node.right, curNode.depth+1));
            }
        }
        return 0;
    }

    public class nodeQueue{
        TreeNode node;
        int depth;
        nodeQueue(TreeNode node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }

    //  11 July 2023
    //  All nodes distance k in binary tree
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        //  If node is the root
        if (root.val == target.val){
            treeDFS(root, 0, k, result);
            return result;
        }
        //  Find the target node and see whether it's in the left or right subtree of the root
        int[] depth = {1};
        TreeNode targetSubtree = findNode(root.left, target, depth);
        boolean targetInLeft = targetSubtree != null;
        if (!targetInLeft){
            depth[0] = 1;
            targetSubtree = findNode(root.right, target, depth);
        }
        System.out.println(depth[0]);

        //  Add root node if depth = k
        if (depth[0] == k){
            result.add(root.val);
        }

        //  If target is in the left subtree, we need to traverse the right subtree to find nodes with distance k - depth
        if (k >= depth[0]){
            if (targetInLeft){
                treeDFS(root.right, 1, k-depth[0], result);
            }
            //  Else traverse the left subtree to find nodes with distance k - depth
            else{
                treeDFS(root.left, 1, k-depth[0], result);
            }
        }
        

        TreeNode tempTarget = target;
        for (int i = 1; i < depth[0]; i++) {
            //  Move up the tree by 1 level each time and add all nodes with distance k - i + depth that is in the other subtree
            TreeNode parent = findParent(root, tempTarget);
            boolean inLeft = parent.left == tempTarget;
            tempTarget = parent;
            if (i == k){
                result.add(parent.val);
                break;
            }
            if (inLeft){
                treeDFS(parent.right, depth[0] - i + 1, k - i + depth[0] - i, result);
            }
            else{
                treeDFS(parent.left, depth[0] - i + 1, k - i + depth[0] - i, result);
            }
        }
        
        //  Add all nodes with distance k in a downward direction from target
        treeDFS(target, 0, k, result);
        return result;
    }

    //  Find target node and the depth it is located in
    public TreeNode findNode(TreeNode node, TreeNode target, int[] depth){
        if (node == null){
            return null;
        }
        if (node == target){
            return node;
        }
        
        TreeNode left = findNode(node.left, target, depth);
        TreeNode right = findNode(node.right, target, depth);
        if (left != null){
            depth[0]++;
            return left;
        }
        if (right != null){
            depth[0]++;
            return right;
        }
        return null;
    }

    //  Add all nodes of certain depth from a given node
    public void treeDFS(TreeNode node, int curDepth, int targetDepth, List<Integer> result){
        if (curDepth > targetDepth){
            return;
        }
        if (node == null){
            return;
        }
        if (curDepth == targetDepth){
            result.add(node.val);
            return;
        }
        treeDFS(node.left, curDepth+1, targetDepth, result);
        treeDFS(node.right, curDepth+1, targetDepth, result);
    }

    public TreeNode findParent (TreeNode node, TreeNode target){
        if (node == null){
            return null;
        }
        if (node.left == target || node.right == target){
            return node;
        }
        TreeNode left = findParent(node.left, target);
        TreeNode right = findParent(node.right, target);
        if (left != null){
            return left;
        }
        if (right != null){
            return right;
        }
        return null;
    }   

    //  12 July 2023
    // FInd eventual safe states
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int length = graph.length;
        List<Integer> result = new ArrayList<>();
        List<Integer>[] reversedGraph = new List[length];
        int[] outgoingEdges = new int[length];
        int[] visited = new int[length];
        Queue<Integer> queue = new LinkedList<>();
    
        //  Reverse the graph
        //  With original graph, graph[i] shows all nodes that node i can reach
        //  With new graph, graph[i] shows all nodes that can reach node i
        for (int i = 0; i < length; i++) {
            reversedGraph[i] = new ArrayList<>();
        }
    
        for (int i = 0; i < length; i++) {
            for (int neighbor : graph[i]) {
                reversedGraph[neighbor].add(i);
                outgoingEdges[i]++;
            }
            if (outgoingEdges[i] == 0) {
                queue.offer(i); // Add nodes with zero incoming edges to the queue
                visited[i] = 1; // Mark them as visited
                result.add(i);  // Add them to the result list
            }
        }
    
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : reversedGraph[node]) {
                outgoingEdges[neighbor]--;
                if (outgoingEdges[neighbor] == 0 && visited[neighbor] != 1) {
                    queue.offer(neighbor);
                    visited[neighbor] = 1;
                    result.add(neighbor);
                }
            }
        }
    
        Collections.sort(result);
        return result;
    }

    //  13 July 2023
    //  Course schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int courseFinished = 0;
        int[] outgoingEdges = new int[numCourses]; // Number of prerequisites for each course
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        //  Graph[i] shows all courses that can be taken after course i is taken
        for (int i = 0; i < prerequisites.length; i++) {
            int preReq = prerequisites[i][1];
            int course = prerequisites[i][0];
            outgoingEdges[course]++;
            graph[preReq].add(course);
        }

        //  Add all courses with no prerequisites into a queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (outgoingEdges[i] == 0){
                queue.offer(i);
                visited[i] = 1;
            }
        }

        while (!queue.isEmpty()){
            int course = queue.poll();
            courseFinished++;
            for (int neighbor : graph[course]){
                outgoingEdges[neighbor]--;
                if (outgoingEdges[neighbor] == 0){
                    queue.offer(neighbor);
                    visited[neighbor] = 1;
                }
            }
        }
        
        if (courseFinished == numCourses){
            return true;
        }
        return false;
    }

    //  14 July 2023
    //Longest arithmetic subsequence of given difference
    public int longestSubsequence(int[] arr, int difference) {
        int SIZE = 20001;
        int[] dp = new int[SIZE];
        int max = 1;

        for (int i : arr){
            int index = i + 10000;
            int k = index - difference;
            if (k >= 0 && k < SIZE){
                dp[index] = dp[k] + 1;
                max = Math.max(max, dp[index]);
            }
            else{
                dp[index] = 1;
            }
        }
        
        return max;
    }

    //  15 July 2023
    //  Maximum number of events that can be attended II
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        int[][] dp = new int[k + 1][n + 1];
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        //  dp[count][index] shows the maximum value that can be obtained by attending 'count' number of events starting from index 'index' to the end of events array
        for (int curIndex = n - 1; curIndex >= 0; curIndex--) {
            for (int count = 1; count <= k; count++) {
                int nextIndex = binarySearch(events, events[curIndex][1]);
                //  We can either choose to take this event, which will be events[curIndex][2] + dp[count - 1][nextIndex]
                //  OR
                //  We can choose to not take this event, which will be dp[count][curIndex + 1] i.e move on to next event
                dp[count][curIndex] = Math.max(dp[count][curIndex + 1], events[curIndex][2] + dp[count - 1][nextIndex]);
            }
        }
        return dp[k][0];
    }
    
    private int binarySearch(int[][] events, int target) {
        //  Searches for closest event with start date greater than target
        int left = 0;
        int right = events.length;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (events[mid][0] <= target){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return left;
    }
}
