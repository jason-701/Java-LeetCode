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
}
