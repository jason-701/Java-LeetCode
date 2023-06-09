public class Main{
    public static void main(String[] args) {
        array_string foo = new array_string();
        two_pointers too = new two_pointers();
        int[] test = {1,2,0,0,44,244,0,0,12,3,5,5,1};
        too.moveZeroes(test);
        for (int c : test) {
            System.out.println(c);
        }
    }
}