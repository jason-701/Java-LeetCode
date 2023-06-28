import java.util.*;

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

    //  Find the path with the maximum probability
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        //  The idea is to use Dijkstra's Algorithm that sorts with the highest probability first
         
        //  Priority queue to store node and weight data
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight).reversed());

        //  Initialize necessary arrays for Djikstra's
        double[] d = new double[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = 0.0; // distance array is set to 0 because we want to get the maximum and not the minimum
            s[i] = 0;            
        }

        //  Store all adjacent edges of each node
        Map<Integer, List<Edge>> adjacentEdges = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adjacentEdges.computeIfAbsent(u, ArrayList::new).add(new Edge(u, v, succProb[i]));
            adjacentEdges.computeIfAbsent(v, ArrayList::new).add(new Edge(v, u, succProb[i]));
        }

        queue.add(new Edge(start, start, 1));
        d[start] = 1;

        while (!queue.isEmpty()){
            Edge temp = queue.poll();
            s[temp.getNode2()] = 1;
            if (temp.getNode2() == end){
                return temp.getWeight();
            }

            List<Edge> edgesToAdd = new ArrayList<>();
            List<Edge> adjacents = adjacentEdges.getOrDefault(temp.getNode2(), new ArrayList<>());

        //  Update probability value of each adjacent edge
            for (Edge edge : adjacents) {
                int node1 = edge.getNode1();
                int node2 = edge.getNode2();
                if (s[node2] != 1 && d[node2] < d[temp.getNode2()] * edge.getWeight()) {
                    d[node2] = d[temp.getNode2()] * edge.getWeight();
                    edgesToAdd.add(new Edge(node1, node2, d[node2]));
                }
            }

            queue.addAll(edgesToAdd); // Add the new edges to the queue


        }
        return 0.0;
    }

    //  Static class to store edge data along with their weight
    private static class Edge{

        private final int node1;
        private final int node2;
        private final double weight;

        //  Constructor
        public Edge(int node1, int node2, double weight){
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        //  Basic functions
        public int getNode1(){
            return this.node1;
        }

        public int getNode2(){
            return this.node2;
        }

        public double getWeight(){
            return this.weight;
        }
    }
}