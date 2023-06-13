import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sliding_window {
    
    //  find sub-array of length k with max average
    public double findMaxAverage(int[] nums, int k) {
        double curMax = 0;
        
        //  compute sum of first sliding window
        for (int i = 0; i < k; i++) {
            curMax += nums[i];
        }

        //  calculate sum of subsequent window, update max sum when necessary
        double temp = curMax;
        for (int i = k; i < nums.length; i++) {
            temp = temp - nums[i-k] + nums[i];
            if (temp > curMax){
                curMax = temp;
            }
            System.out.println(curMax);
        }
        return curMax/k;
    }

    //  find max number of vowels in a substring
    public int maxVowels(String s, int k) {
        List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int maxCount = 0;

        //  calculate no. of vowels in initial sub-window
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))){
                maxCount++;
            }
        }

        //  calculate no of vowels in subsequent sub-window and update results
        int count = maxCount;
        for (int i = k; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i-k))){
                count--;
            }
            if (vowels.contains(s.charAt(i))){
                count++;
            }
            if (count > maxCount){
                maxCount = count;
            }
        }
        return maxCount;
    }

    //  find max consecutive 1s in an array if you can flip a certain number of 0s
    public int longestOnes(int[] nums, int k) {
        int start, end, zeroCount, curMax;
        start = end = zeroCount = curMax = 0;
        while (end < nums.length){
            if (nums[end] == 1 && zeroCount <= k){
                end++;
            }
            else{
                //  if less than max number of 0s allowed, window size still can expand
                if (zeroCount < k){
                    end++;
                    zeroCount++;
                }
                //  max number of 0s reached, move the sliding window
                else{
                    if (curMax < (end - start)){
                        curMax =  end - start;
                    }
                    if (nums[start] == 0){
                        zeroCount--;
                    }
                    start++;
                }
            }
        }

        //  have to take into account if the entire array fits the criteria, as shown by end - start
        return curMax > (end - start) ? curMax : (end - start);
    }
}
