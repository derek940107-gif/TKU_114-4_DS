```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {

    private Map<String, Set<String>> friends;

    public SocialNetworkGraph() {
        friends = new HashMap<>();
    }

    public void addUser(String user) {
        friends.putIfAbsent(user, new HashSet<>());
    }

    public void addFriendship(String user1, String user2) {
        if (user1.equals(user2)) {
            return;
        }

        addUser(user1);
        addUser(user2);

        friends.get(user1).add(user2);
        friends.get(user2).add(user1);
    }

    public void removeFriendship(String user1, String user2) {
        if (!friends.containsKey(user1) || !friends.containsKey(user2)) {
            return;
        }

        friends.get(user1).remove(user2);
        friends.get(user2).remove(user1);
    }

    public Set<String> mutualFriends(String user1, String user2) {
        Set<String> result = new HashSet<>();

        if (!friends.containsKey(user1) || !friends.containsKey(user2)) {
            return result;
        }

        result.addAll(friends.get(user1));
        result.retainAll(friends.get(user2));

        return result;
    }

    public List<String> isolatedUsers() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : friends.entrySet()) {
            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public Set<String> getFriends(String user) {
        if (!friends.containsKey(user)) {
            return new HashSet<>();
        }

        return new HashSet<>(friends.get(user));
    }

    public boolean hasUser(String user) {
        return friends.containsKey(user);
    }

    public int userCount() {
        return friends.size();
    }

    public static void main(String[] args) {
        SocialNetworkGraph graph = new SocialNetworkGraph();

        graph.addUser("Alice");
        graph.addUser("Bob");
        graph.addUser("Charlie");
        graph.addUser("David");
        graph.addUser("Eve");

        graph.addFriendship("Alice", "Bob");
        graph.addFriendship("Alice", "Charlie");
        graph.addFriendship("Bob", "Charlie");
        graph.addFriendship("Bob", "David");

        System.out.println("Alice 的好友：" + graph.getFriends("Alice"));
        System.out.println("Alice 與 Bob 的共同好友："
                + graph.mutualFriends("Alice", "Bob"));

        graph.removeFriendship("Alice", "Bob");

        System.out.println("解除 Alice 與 Bob 的好友關係後：");
        System.out.println("Alice 的好友：" + graph.getFriends("Alice"));
        System.out.println("Bob 的好友：" + graph.getFriends("Bob"));

        System.out.println("孤立用戶：" + graph.isolatedUsers());
    }
}
```
