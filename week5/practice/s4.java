package week5.practice;

class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {

        if (patientId == null)
            patientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {

        if (pin != null && pin.matches("[0-9]{4,6}"))
            lockerPin = pin;
    }
}

public class s4 {

    public static void main(String[] args) {

        PatientProfile p = new PatientProfile();

        p.setPatientId("MT2026-0142");
        p.setPatientId("HACKED-0000");

        System.out.println(p.getPatientId());
    }
}
