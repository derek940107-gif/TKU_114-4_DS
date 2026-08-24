public class RecursiveTextTools {

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() <= 1) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        String cleaned = cleanString(str);
        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static String cleanString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        return isPalindromeHelper(str, left + 1, right - 1);
    }

    public static int countCharacter(String str, char target) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int match = (str.charAt(0) == target) ? 1 : 0;
        return match + countCharacter(str.substring(1), target);
    }

    public static void test(String input, char targetChar) {
        System.out.println("=== 測試字串: \"" + input + "\" ===");
        System.out.println("反轉字串 (reverse): \"" + reverse(input) + "\"");
        System.out.println("是否為回文 (isPalindrome): " + isPalindrome(input));
        System.out.println("字元 '" + targetChar + "' 出現次數 (countCharacter): " + countCharacter(input, targetChar));
        System.out.println();
    }

    public static void main(String[] args) {
        test("", 'a');
        test("A", 'A');
        test("Level", 'e');
        test("Race car", 'c');
        test("Hello World", 'l');
    }
}