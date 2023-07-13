import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};      
        july_challenge test = new july_challenge();
        System.out.println(test.canFinish(2,prerequisites));
    }
}