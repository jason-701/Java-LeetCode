import java.lang.Math;
import java.util.HashMap;

public class two_pointers {
    //  move all 0s in an array to the end
    public void moveZeroes(int[] nums) { 
        int index=0;
        for (int num:nums){
            if (num!=0){
                nums[index++]=num;
            }
        }
        while (index<nums.length){
            nums[index++]=0;
        }
    }
    //  check whether s is a subsequence of t
    public boolean isSubsequence(String s, String t) { 
        if (s.length()==0){
            return true;
        }
        int s_index, t_index;
        s_index = t_index = 0;
        while(t_index<t.length()){
            if (t.charAt(t_index)==s.charAt(s_index)){
                s_index++;
                if (s_index==s.length()){
                return true;
            }
            }
            t_index++;
        }
        return false;   
    }

    //  given an int array, find the max area that can be formed from the x-asis (index) and the y-axis (height[index])
    public int maxArea(int[] height) {
        int leftIndex, rightIndex,curMax;
        leftIndex = curMax = 0;
        rightIndex = height.length-1;
        while (leftIndex<rightIndex){
            //  idea is that the lower one between height[leftIndex] and height[rightindex] won't be able to form
            //  an area that is greater than the current one, hence we remove that index
            curMax = Math.max(curMax, (rightIndex-leftIndex) * (Math.min(height[leftIndex], height[rightIndex])));
            if (height[leftIndex] > height[rightIndex]){
                rightIndex--;
            }
            else{
                leftIndex++;
            }
        }
        return curMax;
    }

    public int maxOperations(int[] nums, int k) {
        HashMap<Integer,Integer> numCount = new HashMap<>();
        int operations = 0;
        for (int i = 0; i < nums.length; i++) {
            //  check if k-nums[i] and nums[i] pair exists in the hashmap
            if (numCount.containsKey(k-nums[i]) && numCount.get(k-nums[i]) > 0 ){
                //  if such pair exists, update operations and the pair count accordingly
                operations++;
                numCount.put(k-nums[i], numCount.get(k-nums[i])-1);
            }
            else{
                //  if not, add this pair into the hashmap
                numCount.put(nums[i], numCount.getOrDefault(nums[i], 0)+1);
            }
        }
        return operations;
    }
}