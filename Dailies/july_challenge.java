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
}
