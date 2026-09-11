package week5.practice;

class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    static {
        System.out.println("Discharge system started");
    }

    public DischargeSummary(String patientId,
                            String[] medicationCodes) {

        if (medicationCodes == null)
            throw new IllegalArgumentException();

        for (String code : medicationCodes) {

            if (code == null ||
                !code.matches("MED-[A-Z]")) {

                throw new IllegalArgumentException();
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 || index >= medicationCodes.length)
            throw new IndexOutOfBoundsException();

        if (newCode == null ||
            !newCode.matches("MED-[A-Z]")) {

            throw new IllegalArgumentException();
        }

        String[] newCodes = medicationCodes.clone();

        newCodes[index] = newCode;

        return new DischargeSummary(patientId, newCodes);
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries == null)
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";

        for (DischargeSummary summary : summaries) {

            if (summary == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary)
                criticalCare++;
            else
                routine++;
        }

        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + criticalCare + " critical-care | "
             + routine + " routine";
    }
}


class CriticalCareDischargeSummary extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);

        this.icuDays = icuDays;
    }
}


public class s5 {

    public static void main(String[] args) {

        DischargeSummary[] summaries = {

            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4
            ),

            null,

            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"}
            )
        };

        System.out.println(
            DischargeSummary.processNightlyBatch(summaries)
        );
    }
}