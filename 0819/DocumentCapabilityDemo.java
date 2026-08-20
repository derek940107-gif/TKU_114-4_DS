interface Exportable {
    void exportFile();
}

interface Compressible {
    void compressFile();
}

class BackupDocument implements Exportable, Compressible {
    private String fileName;

    public BackupDocument(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void exportFile() {
        System.out.println(fileName + " 已匯出");
    }

    @Override
    public void compressFile() {
        System.out.println(fileName + " 已壓縮");
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument document = new BackupDocument("backup.txt");

        Exportable exportable = document;
        Compressible compressible = document;

        exportable.exportFile();
        compressible.compressFile();
    }
}