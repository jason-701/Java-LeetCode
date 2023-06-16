import java.util.Arrays;

public class prefix_sum {

    //  Find the highest alttitude given an array gain where gain[i] is the gain in altitude between gain[i] and gain[i+1]
    public int largestAltitude(int[] gain) {
        int max, sum;
        max = sum = 0;

            //  Iterate through the array while getting the cumulative sum and finding the max
        for (int i = 0; i < gain.length; i++) {
            sum += gain[i];
            max = Math.max(max, sum);
        }
        return max;
    }

    //  Find the pivot index where sum of all elements before the index = sum of all elements after the index
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = 0;
        right[right.length-1] = 0;

        //  Left array = sum of all elements to the left of index
        //  Right array = sum of all elements to the right of index
        for (int i = 1; i < length; i++) {
            left[i] = left[i-1] + nums[i-1];
            right[length-1-i] = right[length-i] + nums[length-i];
        }

        //  If left[i] = right[i], then it is the pivot index
        for (int i = 0; i < length; i++) {
            if (left[i] == right[i]){
                return i;
            }
        }
        return -1;
    }
}
