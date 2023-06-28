public class Main{
    public static void main(String[] args) {
        int[][] nums1 = {{1,4},{2,4},{0,4},{0,3},{0,2},{2,3}};
        double[] succProb = {0.37,0.17,0.93,0.23,0.39,0.04};
        int[] nums2 = {2,4,6};
        daily_challenge test = new daily_challenge();
        System.out.println(test.maxProbability(5,nums1,succProb, 3,4));
    }
}