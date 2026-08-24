import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    private Deque<String> undoStack;
    private Deque<String> redoStack;
    private String currentText;

    public TextEditorHistory() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
        this.currentText = "";
    }

    public void type(String newText) {
        undoStack.push(currentText);
        currentText = currentText + newText;
        redoStack.clear();
        System.out.println("輸入文字: \"" + newText + "\"");
        printState();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("無法撤銷：Undo 棧為空");
            return;
        }
        redoStack.push(currentText);
        currentText = undoStack.pop();
        System.out.println("執行撤銷 (Undo)");
        printState();
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("無法重做：Redo 棧為空");
            return;
        }
        undoStack.push(currentText);
        currentText = redoStack.pop();
        System.out.println("執行重做 (Redo)");
        printState();
    }

    public void printState() {
        System.out.println("  當前文本: \"" + currentText + "\"");
        System.out.println("  Undo 棧: " + undoStack);
        System.out.println("  Redo 棧: " + redoStack);
        System.out.println();
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        System.out.println("=== 測試 1: 空棧狀態測試 ===");
        editor.undo();
        editor.redo();

        System.out.println("=== 測試 2: 文字輸入操作 ===");
        editor.type("Hello ");
        editor.type("World");
        editor.type("!");

        System.out.println("=== 測試 3: 連續撤銷 (Undo) ===");
        editor.undo();
        editor.undo();

        System.out.println("=== 測試 4: 連續重做 (Redo) ===");
        editor.redo();

        System.out.println("=== 測試 5: 輸入新文字以清空 Redo 棧 ===");
        editor.type(" Java");
        editor.redo();

        System.out.println("=== 測試 6: 清空全部 Undo ===");
        editor.undo();
        editor.undo();
        editor.undo();
        editor.undo();
    }
}