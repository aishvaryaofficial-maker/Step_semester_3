package week7.practice;
interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    private String zoneName;

    SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {

    MotionSensor(String zoneName) {
        super(zoneName);
    }

    public String sendAlert(String message) {
        return "[" + getZoneName() + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private String secondZone;

    DualZoneMotionSensor(String zoneName, String secondZone) {
        super(zoneName);
        this.secondZone = secondZone;
    }

    public String sendAlert(String message) {
        return super.sendAlert(message)
                + " [also covering " + secondZone + "]";
    }
}

class SmokeDetector implements Alertable {
    private String detectorId;

    SmokeDetector(String detectorId) {
        this.detectorId = detectorId;
    }

    public String sendAlert(String message) {
        return "[" + detectorId + "] " + message;
    }
}

public class Problem2 {

    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor m = (MotionSensor) a;
            return m.getZoneName();
        }

        return "Not a motion sensor";
    }

    public static void main(String[] args) {

        MotionSensor m =
            new MotionSensor("Living Room");

        DualZoneMotionSensor d =
            new DualZoneMotionSensor("Hallway", "Stairwell");

        SmokeDetector s =
            new SmokeDetector("SD-01");

        System.out.println(m.sendAlert("Motion detected"));
        System.out.println(d.sendAlert("Motion detected"));
        System.out.println(s.sendAlert("Smoke detected"));

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));

        broadcastAll(
            new Alertable[]{m, d, s},
            "Security alert"
        );
    }
}