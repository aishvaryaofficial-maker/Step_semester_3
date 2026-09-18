package week6.assignment;

class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;
    private final int entryCode;
    private static int bibCounter = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }
        if (entryFee <= 0) {
            throw new IllegalArgumentException("Entry fee must be positive");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;

        bibCounter++;
        entryCode = bibCounter;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public int getEntryCode() {
        return entryCode;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))
                || !Character.isDigit(code.charAt(2))
                || !Character.isDigit(code.charAt(3))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(4))
                && Character.isLetter(code.charAt(4));
    }

    public static int getBibCounter() {
        return bibCounter;
    }
}

class RunnerEntry extends RaceEntry {
    protected String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);

        if (teamSize <= 0) {
            throw new IllegalArgumentException("Team size must be positive");
        }

        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}

public class a5 {
    public static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relayCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relayCount + " relay | "
                + individualCount + " individual";
    }

    public static void main(String[] args) {
        EliteRunnerEntry elite = new EliteRunnerEntry(
                "BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        elite.pay(10, "UPI");

        RaceEntry[] entries = {elite, null, relay};

        System.out.println(settleNight(entries));

        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        System.out.println(RaceEntry.getBibCounter());
    }
}