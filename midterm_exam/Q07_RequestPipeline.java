import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : text.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(Deque<String> urgentQueue) {
        return urgentQueue.pollFirst();
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String command : commands) {
            if (command == null || command.trim().isEmpty()) {
                continue;
            }

            String trimmed = command.trim();
            String[] parts = trimmed.split("\\s+");

            if (parts.length == 2 && parts[0].equals("NORMAL")) {
                normalQueue.offerLast(parts[1]);
            } else if (parts.length == 2 && parts[0].equals("URGENT")) {
                urgentQueue.offerLast(parts[1]);
            } else if (parts.length == 1 && parts[0].equals("PROCESS")) {
                if (!urgentQueue.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgentQueue));
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.pollFirst());
                } else {
                    result.add("EMPTY");
                }
            }
        }

        return result;
    }
}