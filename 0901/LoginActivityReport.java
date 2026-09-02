```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoginActivityReport {

    private Map<String, Integer> loginCount;
    private Map<String, Set<String>> userIps;

    public LoginActivityReport() {
        loginCount = new HashMap<>();
        userIps = new HashMap<>();
    }

    public void addLogin(String account, String ip) {
        loginCount.put(account, loginCount.getOrDefault(account, 0) + 1);
        userIps.putIfAbsent(account, new HashSet<>());
        userIps.get(account).add(ip);
    }

    public int getLoginCount(String account) {
        return loginCount.getOrDefault(account, 0);
    }

    public int getDifferentIpCount(String account) {
        if (!userIps.containsKey(account)) {
            return 0;
        }

        return userIps.get(account).size();
    }

    public void printReport(int abnormalThreshold) {
        System.out.println("登入記錄分析報告");

        for (String account : loginCount.keySet()) {
            int count = getLoginCount(account);
            int ipCount = getDifferentIpCount(account);

            System.out.println(
                account + "：登入次數=" + count +
                "，不同IP數=" + ipCount
            );

            if (count >= abnormalThreshold) {
                System.out.println("異常重複登入：" + account);
            }
        }
    }

    public void printAbnormalReport(int abnormalThreshold) {
        System.out.println("異常重複登入報告");

        boolean found = false;

        for (String account : loginCount.keySet()) {
            int count = getLoginCount(account);

            if (count >= abnormalThreshold) {
                System.out.println(
                    account + "：登入 " + count +
                    " 次，不同IP " + getDifferentIpCount(account) + " 個"
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有異常重複登入帳號");
        }
    }

    public static void main(String[] args) {
        LoginActivityReport report = new LoginActivityReport();

        report.addLogin("alice", "192.168.1.10");
        report.addLogin("alice", "192.168.1.10");
        report.addLogin("alice", "192.168.1.20");

        report.addLogin("bob", "192.168.1.30");
        report.addLogin("bob", "192.168.1.30");

        report.addLogin("charlie", "192.168.1.40");
        report.addLogin("charlie", "192.168.1.50");
        report.addLogin("charlie", "192.168.1.60");
        report.addLogin("charlie", "192.168.1.70");

        report.printReport(3);
        System.out.println();
        report.printAbnormalReport(3);
    }
}
```
