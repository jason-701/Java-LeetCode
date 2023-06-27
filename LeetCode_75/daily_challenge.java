import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class daily_challenge{

    //  Find k pairs with smallest sums
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //  Create a priority queue that sorts by sum
        //  Pair with largest sum gets highest priority
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getSum).reversed());
        for (int i = 0; i < nums1.length && i < k; i++) {
            for (int j = 0; j < nums2.length && j < k; j++) {
                if (queue.size()<k){
                queue.add(new Pair(nums1[i], nums2[j]));
                }
        //  If the new pair's sum is less than the sum of the top of the queue, replace it with the new pair
                else if (nums1[i]+nums2[j] < queue.peek().getSum()){
                    queue.poll();
                    queue.add(new Pair(nums1[i], nums2[j]));
                }
            }
        }

        //  Store everything into a new list
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty() && count<k){
            Pair pair = queue.poll();
            List<Integer> innerList = new ArrayList<>();
            innerList.add(pair.getA());
            innerList.add(pair.getB());
            result.add(innerList);
            count++;
        }
        return result;

    }

    private static class Pair{
        private final int a;
        private final int b;
        
        //  Constructor
        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }

        // basic functions
        public int getA(){
            return this.a;
        }

        public int getB(){
            return this.b;
        }

        public int getSum(){
            return this.a + this.b;
        }
    }
}