import java.util.ArrayList;
import java.util.List;

public class Q04_NotificationRouter {

    public interface Channel {
        String name();
        boolean supports(String destination);
        String send(String destination, String message);
    }

    public static class EmailChannel implements Channel {
        @Override
        public String name() {
            return "EMAIL";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }

            int at = destination.indexOf('@');
            return at > 0 && at < destination.length() - 1;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    public static class SmsChannel implements Channel {
        @Override
        public String name() {
            return "SMS";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }

            String number = destination.replace("-", "");

            if (number.length() != 10) {
                return false;
            }

            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    private static void routeCheckpointM26() {
    }

    public static List<String> route(
            List<Channel> channels,
            String destination,
            String message) {

        routeCheckpointM26();

        if (channels == null || destination == null || message == null) {
            return new ArrayList<>();
        }

        List<String> results = new ArrayList<>();

        for (Channel channel : channels) {
            if (channel != null && channel.supports(destination)) {
                results.add(channel.send(destination, message));
            }
        }

        return results;
    }
}