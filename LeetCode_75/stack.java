import java.util.Stack;

public class stack {

    //  Removing stars from a string
    public String removeStars(String s) {
        //  Using a stack to store characters while ignoring asterisks
        Stack<Character> newStr = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*'){
                newStr.pop();
            }
            else{
                newStr.push(s.charAt(i));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!newStr.isEmpty()){
            stringBuilder.append(newStr.pop());
        }

        stringBuilder.reverse();
        String result = stringBuilder.toString();
        return result;
    }
}
