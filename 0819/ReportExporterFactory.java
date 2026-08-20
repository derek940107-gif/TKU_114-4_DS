interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println(title);

        if (values == null) {
            System.out.println();
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);

            if (i < values.length - 1) {
                System.out.print(",");
            }
        }

        System.out.println();
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("{");
        System.out.println("  \"title\": \"" + title + "\",");
        System.out.print("  \"values\": [");

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i]);

                if (i < values.length - 1) {
                    System.out.print(", ");
                }
            }
        }

        System.out.println("]");
        System.out.println("}");
    }
}

class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        for (int value : values) {
            System.out.println("數值：" + value);
        }
    }
}

public class ReportExporterFactory {

    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }

        if (format.equalsIgnoreCase("csv")) {
            return new CsvExporter();
        }

        if (format.equalsIgnoreCase("json")) {
            return new JsonExporter();
        }

        return new TextExporter();
    }

    public static void exportReport(
            ReportExporter exporter,
            String title,
            int[] values) {

        if (exporter == null) {
            return;
        }

        exporter.export(title, values);
    }

    public static void main(String[] args) {
        int[] values = {10, 20, 30, 40};

        ReportExporter csv = createExporter("csv");
        ReportExporter json = createExporter("json");
        ReportExporter text = createExporter("pdf");

        exportReport(csv, "CSV報表", values);
        System.out.println();

        exportReport(json, "JSON報表", values);
        System.out.println();

        exportReport(text, "文字報表", values);
        System.out.println();

        exportReport(json, "空資料報表", null);
    }
}