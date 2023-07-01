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
}
