import java.util.ArrayList;
import java.util.List;

public class functions {
    public void merge_strings_alternatively(String a, String b){
        int i;
        i=0;
        String result="";
        while(i<a.length() && i<b.length()){
            result+=a.charAt(i);
            result+=b.charAt(i);
            i++;
        }
        if (i<a.length()){
            result = result.concat(a.substring(i,a.length()));
        }
        if (i<b.length()){
            result = result.concat(b.substring(i,b.length()));
        }
        System.out.println(result);

    }
    public String gcdOfStrings(String str1, String str2) {  //this is actually pretty cool
        int len1 = str1.length();                           // the greatest common string divisor for 2 strings is the same
        int len2 = str2.length();                           // length as the gcd of the length of the 2 strings
        int common_den = gcd(len1, len2);
        String test  =str1.substring(0, common_den);
        String combined = str1 + str2;
        for (int i = 0; i < len1+len2; i+=common_den) {
            if (!(combined.startsWith(test, i))){
                return "";
            }
        }
        System.out.println(test);
        return test;
    }
    private int gcd(int a, int b){
        return a == 0 ? b : gcd(b % a, a);
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int index = 0; index < candies.length; index++) {
            if (candies[index]>max){
                max = candies[index];
            }
        }
        List<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < candies.length; i++) {
            if (candies[i]+extraCandies>=max){
                result.add(i, true);
            }
            else{
                result.add(i, false);
            }
        }
        return result;
    }
}
