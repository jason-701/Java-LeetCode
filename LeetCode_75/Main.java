public class Main{
    public static void main(String[] args) {
        two_pointers test = new two_pointers();
        int[] test_array = {1,2,0,0,44,244,0,0,12,3,5,5,1};
        test.moveZeroes(test_array);
        for (int c : test_array) {
            System.out.println(c);
        }
    }
}