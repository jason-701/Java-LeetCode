import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        int[][] events = {{1,2,4},{3,4,3},{2,3,10}};
        july_challenge test = new july_challenge();
        System.out.println(test.maxValue(events,2));
    }
}