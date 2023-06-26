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

    //  Asteroid collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> asteroid = new Stack<>();
        asteroid.push(asteroids[0]);
        int firstAsteroid, secondAsteroid;
        for (int i = 1; i < asteroids.length; i++) {
            //  If the stack is not empty, take one out to do comparison with the current asteroids[i]
            if (!asteroid.isEmpty()){
                firstAsteroid = asteroid.pop();
                secondAsteroid = asteroids[i];
            }
            else{
                asteroid.push(asteroids[i]);
                continue;
            }
            
            //  If the 2 asteroids are colliding
            while (firstAsteroid > 0 && secondAsteroid < 0 && !asteroid.isEmpty()){
                if (Math.abs(secondAsteroid)!=Math.abs(firstAsteroid)){
                    secondAsteroid = Math.abs(secondAsteroid)>Math.abs(firstAsteroid) ? secondAsteroid : firstAsteroid;
                    firstAsteroid = asteroid.pop();
                }
            //  Same absolute value means both asteroids disappear
                else{
                    break;
                }
            }

            //  If not colliding, then both asteroids stay
            if (firstAsteroid>0 && secondAsteroid > 0 || firstAsteroid<0 && secondAsteroid<0 || firstAsteroid<0 && secondAsteroid>0){
                asteroid.push(firstAsteroid);
                asteroid.push(secondAsteroid);
            }
            //  Else if colliding, only save the one that's larger
            else{
                if (Math.abs(secondAsteroid) != Math.abs(firstAsteroid)){
                    asteroid.push(Math.abs(secondAsteroid)>Math.abs(firstAsteroid) ? secondAsteroid : firstAsteroid);
                }
            }
        }

        // Store final result in an array
        int[] result = new int[asteroid.size()];
        int i = result.length-1;
        while(!asteroid.isEmpty()){
            result[i--] = asteroid.pop();
        }
        return result;  
    }

    //  Given an encoded string, return its decoded string.
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // Keep traversing the string until there is a closing bracket
            if (s.charAt(i) != ']'){
                stack.push(s.charAt(i));
            }
            
            else{
                char temp = stack.pop();
                while (temp != '['){
                    stringBuilder.append(temp);
                    temp = stack.pop();
                }
            //  The character right before the opening bracket will be the number of times the string has to be repeated
            //  This portion of the code takes into account the number can be nulti-digit
                int count;
                char digit;
                digit = stack.pop();
                count = 0;
                int multiplier = 1;
                while (Character.isDigit(digit)){
                    count = count + Character.getNumericValue(digit) * multiplier;
                    multiplier *= 10;
                    if (!stack.isEmpty()){
                        digit = stack.pop();
                    }
                    else{
                        break;
                    }
                    
                }
                if (!Character.isDigit(digit)){
                    stack.push(digit);
                }
                

            //  Create a string that is repeated a certain number of times
                stringBuilder.reverse();
                String str = stringBuilder.toString();
                for (int j = 1; j < count; j++) {
                    stringBuilder.append(str);
                }

            // Push result string back into the stack
                char[] arr = stringBuilder.toString().toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    stack.push(arr[j]);
                }
            
            //  Reset stringBuilder
                stringBuilder.setLength(0);
            }
        }
        stringBuilder.setLength(0);
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }
}
