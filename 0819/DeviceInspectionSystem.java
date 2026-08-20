class Device {
    protected String name;

    public Device(String name) {
        this.name = name;
    }

    public void runDiagnostic() {
        System.out.println(name + " 正在進行設備檢測");
    }
}

class Laptop extends Device {
    public Laptop(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(name + " 正在進行筆記型電腦檢測");
    }
}

class Printer extends Device {
    public Printer(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(name + " 正在進行印表機檢測");
    }

    public void cleanPrintHead() {
        System.out.println(name + " 正在清潔印字頭");
    }
}

class Router extends Device {
    public Router(String name) {
        super(name);
    }

    @Override
    public void runDiagnostic() {
        System.out.println(name + " 正在進行路由器檢測");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = new Device[4];

        devices[0] = new Laptop("ASUS Laptop");
        devices[1] = new Printer("HP Printer");
        devices[2] = new Router("TP-Link Router");
        devices[3] = new Printer("Canon Printer");

        for (Device device : devices) {
            device.runDiagnostic();

            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}