import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;
        while(i<flowerbed.length){
            if (flowerbed[i]==1){
                i+=2;
            }
            else if (i!=0 && i<flowerbed.length-1){
                if (flowerbed[i-1]==0 && flowerbed[i+1]==0){
                    n--;
                    i+=2;
                }
                else if (flowerbed[i+1]!=0){
                    i+=3;
                }
                else{
                    i++;
                }
            }
            else if (i!=0 && i==flowerbed.length-1){
                if (flowerbed[i-1]==0){
                    n--;
                    i++;
                }
                else{
                    i++;
                }
            }
            else{
                if (i==flowerbed.length-1){
                    n--;
                    i++;
                }
                else if (flowerbed[i+1]==0){
                    n--;
                    i+=2;
                }
                else{
                    i+=3;
                }
            }
        }
        if (n<=0){
            return true;
        }
        else{
            return false;
        }
    }

    public String reverseVowels(String s) {// String type is immutable, so we use StringBuilder instead
        /*List<Character> vowels = new ArrayList<>(Arrays.asList('a','A','e','E','i','I','o','O','u','U'));
        ArrayList<Character> vowelsInString = new ArrayList<>();
        int i = 0;
        while (i<s.length()){
            if (vowels.contains(s.charAt(i))){
                vowelsInString.add(s.charAt(i));
            }
            i++;
        }
        i=0;
        int j = vowelsInString.size()-1;
        StringBuilder newString = new StringBuilder(s);
        while(i<s.length()){
            if (vowels.contains(s.charAt(i))){
                newString.setCharAt(i, vowelsInString.get(j));
                j--;
            }
            i++;
        }
        return newString.toString();*/
        
        //nvm i'm just stupid we can just use pointers
        List<Character> vowels = new ArrayList<>(Arrays.asList('a','A','e','E','i','I','o','O','u','U'));
        char[] stringArray = s.toCharArray();
        int start = 0;
        int end = stringArray.length-1;
        while (start<end){
            while(start<end && !(vowels.contains(stringArray[start]))){
                start++;
            }
            while(start<end && !(vowels.contains(stringArray[end]))){
                end--;
            }
            char temp = stringArray[start];
            stringArray[start]=stringArray[end];
            stringArray[end]=temp;
            start++;
            end--;
        }
        String finalResult = new String(stringArray);
        return finalResult;
    }
}
