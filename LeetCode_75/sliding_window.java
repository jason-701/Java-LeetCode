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
}
