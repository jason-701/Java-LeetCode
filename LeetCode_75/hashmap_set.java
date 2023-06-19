import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
}
