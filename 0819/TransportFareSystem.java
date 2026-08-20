abstract class Transport {
    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public abstract int calculateFare(int distance);

    public String getRouteName() {
        return routeName;
    }
}

class Bus extends Transport {
    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public int calculateFare(int distance) {
        return 15 + distance * 2;
    }
}

class Taxi extends Transport {
    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public int calculateFare(int distance) {
        return 70 + distance * 10;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = new Transport[4];

        transports[0] = new Bus("紅樹林到淡水");
        transports[1] = new Taxi("淡水到台北車站");
        transports[2] = new Bus("台北車站到士林");
        transports[3] = new Taxi("士林到陽明山");

        int[] distances = {5, 10, 8, 12};

        for (int i = 0; i < transports.length; i++) {
            System.out.println(
                transports[i].getRouteName() +
                "，距離：" + distances[i] +
                " 公里，票價：" +
                transports[i].calculateFare(distances[i]) +
                " 元"
            );
        }
    }
}