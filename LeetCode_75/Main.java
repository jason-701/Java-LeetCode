public class Main{
    public static void main(String[] args) {
        //int[][] cells = {{1,1},{1,2},{2,1},{2,2}};
        int[][] requests = {{1,2},{1,2},{2,2},{0,2},{2,1},{1,1},{1,2}};
        july_challenge test = new july_challenge();
        System.out.println(test.maximumRequests(3, requests));
        
    }
}