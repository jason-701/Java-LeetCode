public class Main{
    public static void main(String[] args) {
        //int[][] cells = {{1,1},{1,2},{2,1},{2,2}};
        int[][] cells = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}};
        june_challenge test = new june_challenge();
        System.out.println(test.latestDayToCross(3,3, cells));
        
    }
}