import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class array_string {
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
    
    public String reverseWords(String s) {
        String trimmedText = s.trim();
        int i = trimmedText.length()-1; //to indicate the end of the word (since we will be iterating from behind)
        int j = i; //j will iterate the string char by char until it hits a white space, which means a complete word
        StringBuilder builder = new StringBuilder();
        while (j>=0){
            if (j!=0 && trimmedText.charAt(j)!=' '){
                j--;
            }// Hello World
            else if (j==0){
                builder.append(trimmedText.substring(j, i+1));
                break;
            }
            else if (i==j){
                j--;
                i=j;
            }
            else{
                builder.append(trimmedText.substring(j+1, i+1));
                builder.append(" ");
                j--;
                i=j;
            }
        }
        String result = builder.toString();
        return result.trim();
    }

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length]; //left[i] is the product of all elements on the left of left[i]
        int[] right = new int[nums.length]; //right[i] is the product of all elements on the right of right[i]

        for (int i = 0; i < left.length; i++) {
            left[i]=1;
            right[i]=1;
        }

        for (int i = 1; i < left.length; i++) {
            left[i]=left[i-1]*nums[i-1];
        }
        
        for (int i = right.length-2; i >=0; i--) {
            right[i]=right[i+1]*nums[i+1];
        }


        int[]result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = left[i]*right[i];
        }
        return result;
    }

    public boolean increasingTriplet(int[] nums) {
        int min1; //tracks the smallest number so far
        long min2; //tracks the second smallest number so far
        min1=nums[0];
        min2=2147483648L;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i]<min1){
                min1=nums[i];
            }
            else if(nums[i]<min2 && nums[i]>min1){
                min2=nums[i];
            }
            else if (nums[i]>min2 && min2>min1){
                return true;
            }   //while iterating, if there exists a number greater than min1 and min2, then a increasing triplet exists
        }
        return false;
    }
}
