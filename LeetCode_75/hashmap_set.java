import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class hashmap_set {

    //  Find the difference of two arrays
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        //  Add unique elements of each array into a new hashset
        //  Hashset.add() only adds if the set doesn't already contain the element
        HashSet<Integer> num1Count = new HashSet<>();
        HashSet<Integer> num2Count = new HashSet<>();
        for (int n : nums1){
            num1Count.add(n);
        }

        for (int n : nums2){
            num2Count.add(n);
        }

        List<Integer> zero = new ArrayList<>();
        List<Integer> one = new ArrayList<>(); 
        for (int n : num1Count){
            if (!num2Count.contains(n)){
                zero.add(n);
            }
        }
        for (int n : num2Count){
            if (!num1Count.contains(n)){
                one.add(n);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(0, zero);
        result.add(1,one);
        return result;
    }

    //  Determine whether the number of occurrences of each int in an array is unique
    public boolean uniqueOccurrences(int[] arr) {

        //  Use hashmap to store each number-occurrences pair
        HashMap<Integer,Integer> count = new HashMap<>();
        for (int n : arr){
            count.put(n, count.getOrDefault(n, 0)+1);
        }

        //  Store each occurrences in a list
        //  Return false if that number already exists
        List<Integer> occurrences = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry : count.entrySet()) {
            System.out.println(entry.getValue());
            if (occurrences.contains(entry.getValue())){
                return false;
            }
            occurrences.add(entry.getValue());
        }
        return true;
    }
}
