package week5.assignments;
class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Loan system started");
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        if (bookIds == null)
            throw new IllegalArgumentException();

        for (String id : bookIds) {

            if (id == null || !id.matches("BK-[0-9]{3}"))
                throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        if (index < 0 || index >= bookIds.length)
            throw new IndexOutOfBoundsException();

        if (newId == null || !newId.matches("BK-[0-9]{3}"))
            throw new IllegalArgumentException();

        String[] newBooks = bookIds.clone();
        newBooks[index] = newId;

        return new LoanReceipt(memberId, newBooks);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null)
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
            }
            else {
                processed++;

                if (receipt instanceof ReferenceOnlyLoanReceipt)
                    referenceOnly++;
                else
                    regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}


class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}


public class a5 {

    public static void main(String[] args) {

        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"}
            )
        };

        System.out.println(
                LoanReceipt.processNightlyCirculation(receipts)
        );
    }
}