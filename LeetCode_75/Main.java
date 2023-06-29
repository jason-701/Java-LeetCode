public class Main{
    public static void main(String[] args) {
        String[] grid = {"@...a",".###A","b.BCc"};
        daily_challenge test = new daily_challenge();
        System.out.println(test.shortestPathAllKeys(grid));
    }
}