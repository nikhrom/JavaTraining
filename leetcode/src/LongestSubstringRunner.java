import java.util.HashSet;
import java.util.Set;

public class LongestSubstringRunner {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("asdaffkl;"));
    }

    static public int lengthOfLongestSubstring(String s) {
        Set<Character> substringWithoutRepeating = new HashSet<>();
        int maxSize = 0;
        for (int i = 0; i < s.length(); i++) {
            Character insertedCharacter = s.charAt(i);
            if(!substringWithoutRepeating.add(insertedCharacter)){
                do{
                    i--;
                }while (!insertedCharacter.equals(s.charAt(i)));

                maxSize = Math.max(maxSize, substringWithoutRepeating.size());
                substringWithoutRepeating.clear();
            };
        }

        return Math.max(maxSize, substringWithoutRepeating.size());
    }
}
