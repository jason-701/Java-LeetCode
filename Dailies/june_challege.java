import java.util.*;

public class june_challege{

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

    //  Shortest path to get all keys
    public int shortestPathAllKeys(String[] grid) {
        // Use BFS to search for all possible states that we can travel to

        Queue<State> queue = new LinkedList<>();

        //  Store all the keys required for the BFS to be considered complete
        List<Character> keysRequired = new ArrayList<>();
        for (String row : grid){
            for (char cell : row.toCharArray()){
                if (Character.isLowerCase(cell)){
                    keysRequired.add(cell);
                }
            }
        }

        //  HashSet to store all visited nodes so far
        Set<State> visitedStates = new HashSet<>();

        //  Move count for BFS
        int moves = 0;

        int startRow, startColumn;
        startRow = startColumn = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == '@') {
                    startRow = i;
                    startColumn = j;
                    break;
                }
            }
        }
        //  Add starting point to queue
        State start = new State(startRow, startColumn, new char[0]);
        queue.add(start);
        queue.add(null);

        //  Apply BFS
        while(!queue.isEmpty()){
            State curState = queue.remove();

            //  Counts the number of move/level traversed
            if (curState == null){
                moves++;
                queue.add(null);
                //  End of queue reached
                if (queue.peek() == null){
                    break;
                }
                else{
                    continue;
                }
            }

            //  Already went to this state
            if (visitedStates.contains(curState)){
                continue;
            } 
            visitedStates.add(curState);

            //  Check if current state is the exit condition
            boolean goalConditionMet = true;
            char[] keysCollected = curState.getKeyscollected();
            for (char key : keysRequired) {
                boolean keyFound = false;
                for (char keysSoFar : keysCollected){
                    if ((key == keysSoFar)){
                        keyFound = true;
                        break;
                    }
                } 
                if (!keyFound){
                    goalConditionMet = false;
                    break;
                }
            }
            if (goalConditionMet) {
                return moves;
            }

            int newRow, newColumn;

            //  Travel up one row
            if (curState.getRow()>0){
                newRow = curState.getRow()-1;
                newColumn = curState.getColumn();
                Boolean canTravel = canTravel(curState, newRow, newColumn, grid);
                char[] newKeys;
                if (canTravel){
                    if (Character.isLowerCase(grid[newRow].charAt(newColumn))){
                        newKeys = getNewKey(keysCollected, newRow, newColumn, grid);
                    }
                    else{
                        newKeys = keysCollected;
                    }
                    State newState = new State(newRow, newColumn, newKeys);
                    queue.add(newState);
                }
            }

            // Travel down one row
            if (curState.getRow()<grid.length-1){
                newRow = curState.getRow()+1;
                newColumn = curState.getColumn();
                Boolean canTravel = canTravel(curState, newRow, newColumn, grid);
                char[] newKeys;
                if (canTravel){
                    if (Character.isLowerCase(grid[newRow].charAt(newColumn))){
                        newKeys = getNewKey(keysCollected, newRow, newColumn, grid);
                    }
                    else{
                        newKeys = keysCollected;
                    }
                    State newState = new State(newRow, newColumn, newKeys);
                    queue.add(newState);
                }
            }

            //  Travel right one column
            if (curState.getColumn()<grid[0].length()-1){
                newRow = curState.getRow();
                newColumn = curState.getColumn()+1;
                Boolean canTravel = canTravel(curState, newRow, newColumn, grid);
                char[] newKeys;
                if (canTravel){
                    if (Character.isLowerCase(grid[newRow].charAt(newColumn))){
                        newKeys = getNewKey(keysCollected, newRow, newColumn, grid);
                    }
                    else{
                        newKeys = keysCollected;
                    }
                    State newState = new State(newRow, newColumn, newKeys);
                    queue.add(newState);
                }
            }

            //  Travel left one column
            if (curState.getColumn()>0){
                newRow = curState.getRow();
                newColumn = curState.getColumn()-1;
                Boolean canTravel = canTravel(curState, newRow, newColumn, grid);
                char[] newKeys;
                if (canTravel){
                    if (Character.isLowerCase(grid[newRow].charAt(newColumn))){
                        newKeys = getNewKey(keysCollected, newRow, newColumn, grid);
                    }
                    else{
                        newKeys = keysCollected;
                    }
                    State newState = new State(newRow, newColumn, newKeys);
                    queue.add(newState);
                }
            }
        }
        return -1;
    }

    private boolean canTravel(State curState, int newRow, int newColumn, String[] grid){
        char test = grid[newRow].charAt(newColumn);
        //  empty cell
        if (test == '.'){
            return true;
        }
        //  wall
        else if (test == '#'){
            return false;
        }
        //  lock
        else if (Character.isUpperCase(test)){
            for (char curKey : curState.getKeyscollected()){
                if (Character.toLowerCase(test)==curKey){
                    return true;
                }
            }
            return false;
        }
        //  key
        else{
            return true;
        }
    }

    private char[] getNewKey(char[] keysCollected, int row, int column, String[] grid){
        char newKey = grid[row].charAt(column);
        for (char key : keysCollected){
            if (key == newKey){
                return keysCollected;
            }
        }
        char[] newKeys = Arrays.copyOf(keysCollected, keysCollected.length + 1);
        newKeys[keysCollected.length] = newKey;
        return newKeys;
    }

    //  Static class to store the state of the exploration
    private static class State{
        private final int row;
        private final int column;
        private final char[] keysCollected;

        //  Constructor
        public State(int row, int column, char[] keysCollected){
            this.row = row;
            this.column = column;
            this.keysCollected = keysCollected;
        }

        // Basic functions
        public int getRow(){
            return this.row;
        }

        public int getColumn(){
            return this.column;
        }

        public char[] getKeyscollected(){
            return this.keysCollected;
        }

        //  To check if 2 state are equal (same row, same column, same keys)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            State other = (State) obj;
            return row == other.row &&
                    column == other.column &&
                    Arrays.equals(keysCollected, other.keysCollected);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(row, column);
            result = 31 * result + Arrays.hashCode(keysCollected);
            return result;
        }
    }
}

