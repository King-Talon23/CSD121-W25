
public class StrUtils {

    /**
     * Checks if the given string is a palindrome
     * (A palindrome is a String that's the same forwards and backwards).
     *
     * @param str the string to check
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String str) {
        // im gonna be honest i dont know where im supposed to use this
        // it wasnt mentioned in the instructions.doc but i just saw the requirement in the rubric
        if (str == null) {
            // I had this also test if the string was empty before but I think
            // an empty string also counts as a palindrome technically cause its always the same
            return false;
        }
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equalsIgnoreCase(reversed);
    }
}

