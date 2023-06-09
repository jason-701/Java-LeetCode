public class two_pointers {
    //  move all 0s in an array to the end
    public void moveZeroes(int[] nums) { 
        int index=0;
        for (int num:nums){
            if (num!=0){
                nums[index++]=num;
            }
        }
        while (index<nums.length){
            nums[index++]=0;
        }
    }
    //  check whether s is a subsequence of t
    public boolean isSubsequence(String s, String t) { 
        if (s.length()==0){
            return true;
        }
        int s_index, t_index;
        s_index = t_index = 0;
        while(t_index<t.length()){
            if (t.charAt(t_index)==s.charAt(s_index)){
                s_index++;
                if (s_index==s.length()){
                return true;
            }
            }
            t_index++;
        }
        return false;   
    }
}
