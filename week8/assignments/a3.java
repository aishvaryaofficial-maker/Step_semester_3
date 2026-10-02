package week8.assignments;
import java.util.*;

interface Capability {
    String name();
    void setValue(int value);
}

class Power implements Capability {
    boolean on = false;

    public String name() {
        return "Power";
    }

    public void setValue(int value) {
        if (value != 0 && value != 1) {
            System.out.println("Invalid Power value.");
            return;
        }

        on = value == 1;
        System.out.println("Power: " + (on ? "ON" : "OFF"));
    }
}

class Brightness implements Capability {
    public String name() {
        return "Brightness";
    }

    public void setValue(int value) {
        if (value < 0 || value > 100) {
            System.out.println(
                "Brightness must be between 0 and 100%.");
            return;
        }

        System.out.println("Brightness set to " + value + "%.");
    }
}

class Temperature implements Capability {
    public String name() {
        return "Temperature";
    }

    public void setValue(int value) {
        if (value < 16 || value > 30) {
            System.out.println(
                "Temperature must be between 16°C and 30°C.");
            return;
        }

        System.out.println("Temperature set to " + value + "°C.");
    }
}

class Device {
    String name;
    ArrayList<Capability> capabilities = new ArrayList<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability c) {
        capabilities.add(c);
        System.out.println(
            name + ": " + c.name() + " capability added.");
    }

    Capability get(String type) {
        for (Capability c : capabilities)
            if (c.name().equals(type))
                return c;

        return null;
    }

    void set(String type, int value) {
        Capability c = get(type);

        if (c != null)
            c.setValue(value);
        else
            System.out.println(
                name + " does not support " + type);
    }
}

public class a3 {
    public static void main(String[] args) {

        Device ac = new Device("Lab AC");
        ac.addCapability(new Power());
        ac.addCapability(new Temperature());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new Power());
        lights.addCapability(new Brightness());

        Device projector = new Device("Projector");
        projector.addCapability(new Power());

        System.out.println("\nLecture Mode:");

        ac.set("Power", 1);
        lights.set("Power", 1);
        projector.set("Power", 1);

        lights.set("Brightness", 40);
        ac.set("Temperature", 24);

        System.out.println("\nAdmin changes:");

        ac.set("Temperature", 12);

        projector.addCapability(new Brightness());
        projector.set("Brightness", 70);
    }
}