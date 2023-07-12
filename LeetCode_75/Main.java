import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        String s = "aababbb"; 
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};       
        july_challenge test = new july_challenge();
        System.out.println(test.eventualSafeNodes(graph));
    }
}