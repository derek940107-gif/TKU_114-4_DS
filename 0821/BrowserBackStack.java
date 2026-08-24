import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    private Deque<String> history;
    private String currentUrl;

    public BrowserBackStack() {
        this.history = new ArrayDeque<>();
        this.currentUrl = null;
    }

    public void visit(String url) {
        if (url == null || url.trim().isEmpty()) {
            return;
        }
        if (this.currentUrl != null) {
            this.history.push(this.currentUrl);
        }
        this.currentUrl = url;
        System.out.println("造訪網頁: " + this.currentUrl);
    }

    public String back() {
        if (this.history.isEmpty()) {
            System.out.println("無法返回：無歷史紀錄");
            return this.currentUrl;
        }
        this.currentUrl = this.history.pop();
        System.out.println("返回至: " + this.currentUrl);
        return this.currentUrl;
    }

    public String getCurrent() {
        if (this.currentUrl == null) {
            System.out.println("目前網頁: 無 (尚未造訪任何網頁)");
            return null;
        }
        System.out.println("目前網頁: " + this.currentUrl);
        return this.currentUrl;
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        System.out.println("=== 開始瀏覽器返回功能測試 ===");

        browser.getCurrent();
        browser.back();

        browser.visit("https://www.google.com");
        browser.getCurrent();

        browser.visit("https://www.github.com");
        browser.visit("https://www.stackoverflow.com");

        browser.back();
        browser.back();
        browser.getCurrent();

        browser.back();
        browser.getCurrent();

        browser.visit("https://www.wikipedia.org");
        browser.getCurrent();
    }
}