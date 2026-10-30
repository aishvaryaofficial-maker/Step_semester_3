package week10.practice;

import java.util.*;

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void energy();
}

abstract class Device {
    String id;
    boolean on;

    Device(String id) {
        this.id = id;
    }

    void power(boolean state) {
        on = state;
        System.out.println(id + " is " + (on ? "ON" : "OFF"));
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    int units;

    Plug(String id, int units) {
        super(id);
        this.units = units;
    }

    public void energy() {
        System.out.println(id + " energy " + units + " kWh");
    }
}

public class p1 {
    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));
        String[] commands = { "ON L1", "DIM L1 40", "DIM F1 30", "SCHEDULE F1 22:00", "ENERGY P1", "ENERGY L1" };
        for (String command : commands) {
            String[] p = command.split(" ");
            Device d = devices.get(p[1]);
            if (p[0].equals("ON")) {
                d.power(true);
            } else if (p[0].equals("DIM")) {
                if (d instanceof Dimmable)
                    ((Dimmable) d).dim(Integer.parseInt(p[2]));
                else
                    System.out.println(d.id + " rejected: DIM unsupported");
            } else if (p[0].equals("SCHEDULE")) {
                if (d instanceof Schedulable)
                    ((Schedulable) d).schedule(p[2]);
                else
                    System.out.println(d.id + " rejected: SCHEDULE unsupported");
            } else if (p[0].equals("ENERGY")) {
                if (d instanceof EnergyMonitor)
                    ((EnergyMonitor) d).energy();
                else
                    System.out.println(d.id + " rejected: ENERGY unsupported");
            }
        }
    }
}
