import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        int[] batteries = {10,10,3,5};
        july_challenge test = new july_challenge();
        //System.out.println(convert("[[-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]]"));
        System.out.println(test.maxRunTime(3,batteries));
    }

    //  function that converts square brackets to curly brackets
    public static String convert(String str){
        String result = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '['){
                result += '{';
            }
            else if(str.charAt(i) == ']'){
                result += '}';
            }
            else{
                result += str.charAt(i);
            }
        }
        return result;
    }
}