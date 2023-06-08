public class Main{
    public static void main(String[] args) {
        array_string foo = new array_string();
        int[] test = {1,0,0,0,0,0,0,0,1000000000};
        char[] teeee = {'a','b','c','c','c','c'};
        char[] beeee = {'a'};
        System.out.println(foo.compress(teeee));
        for (char c : teeee) {
            System.out.println(c);
        }
    }
}