public class Main{
    public static void main(String[] args) {
        int[] test_array = {0,1,1,1,0,1,1,0,1};
        sliding_window test = new sliding_window();
        System.out.println(test.longestSubarray(test_array));  
    }
}