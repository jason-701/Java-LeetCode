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
}
