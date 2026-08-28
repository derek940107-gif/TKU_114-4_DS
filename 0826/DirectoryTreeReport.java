```java
public class DirectoryTreeReport {

    static class Node {
        String name;
        boolean directory;
        int size;
        Node firstChild;
        Node nextSibling;

        Node(String name, boolean directory, int size) {
            this.name = name;
            this.directory = directory;
            this.size = size;
        }

        void addChild(Node child) {
            if (firstChild == null) {
                firstChild = child;
            } else {
                Node current = firstChild;

                while (current.nextSibling != null) {
                    current = current.nextSibling;
                }

                current.nextSibling = child;
            }
        }
    }

    static class Report {
        int totalNodes;
        int fileCount;
        int directoryCount;
        int height;
        Node largestFile;
    }

    public static int calculateSize(Node node) {
        if (node == null) {
            return 0;
        }

        if (!node.directory) {
            return node.size;
        }

        int total = 0;
        Node child = node.firstChild;

        while (child != null) {
            total += calculateSize(child);
            child = child.nextSibling;
        }

        node.size = total;
        return total;
    }

    public static void collectReport(
            Node node,
            int depth,
            Report report) {

        if (node == null) {
            return;
        }

        report.totalNodes++;

        if (node.directory) {
            report.directoryCount++;
        } else {
            report.fileCount++;

            if (report.largestFile == null
                    || node.size > report.largestFile.size) {
                report.largestFile = node;
            }
        }

        if (depth > report.height) {
            report.height = depth;
        }

        Node child = node.firstChild;

        while (child != null) {
            collectReport(child, depth + 1, report);
            child = child.nextSibling;
        }
    }

    public static void printDirectorySizes(Node node) {
        if (node == null) {
            return;
        }

        Node child = node.firstChild;

        while (child != null) {
            printDirectorySizes(child);
            child = child.nextSibling;
        }

        if (node.directory) {
            System.out.println(
                node.name + " = " + node.size
            );
        }
    }

    public static void main(String[] args) {
        Node root = new Node("root", true, 0);

        Node documents = new Node(
            "documents",
            true,
            0
        );

        Node images = new Node(
            "images",
            true,
            0
        );

        Node notes = new Node(
            "notes.txt",
            false,
            120
        );

        Node report = new Node(
            "report.pdf",
            false,
            800
        );

        Node photo1 = new Node(
            "photo1.jpg",
            false,
            1500
        );

        Node photo2 = new Node(
            "photo2.jpg",
            false,
            2200
        );

        Node backup = new Node(
            "backup.zip",
            false,
            3000
        );

        root.addChild(documents);
        root.addChild(images);
        root.addChild(backup);

        documents.addChild(notes);
        documents.addChild(report);

        images.addChild(photo1);
        images.addChild(photo2);

        calculateSize(root);

        Report result = new Report();
        collectReport(root, 1, result);

        System.out.println("Directory Sizes:");
        printDirectorySizes(root);

        System.out.println(
            "totalNodes = " + result.totalNodes
        );

        System.out.println(
            "fileCount = " + result.fileCount
        );

        System.out.println(
            "directoryCount = " + result.directoryCount
        );

        System.out.println(
            "height = " + result.height
        );

        if (result.largestFile != null) {
            System.out.println(
                "largestFile = "
                + result.largestFile.name
                + "|" + result.largestFile.size
            );
        }
    }
}
```
