package week5.assignments;
class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        int privateA = 0, privateD = 0;
        int defaultA = 0, defaultD = 0;
        int protectedA = 0, protectedD = 0;
        int publicA = 0, publicD = 0;

        for (String[] a : attempts) {

            String result = classifyAccess(a[0], a[1]);

            if (a[0].equals("private")) {
                if (result.equals("ALLOWED")) privateA++;
                else privateD++;
            }

            else if (a[0].equals("default")) {
                if (result.equals("ALLOWED")) defaultA++;
                else defaultD++;
            }

            else if (a[0].equals("protected")) {
                if (result.equals("ALLOWED")) protectedA++;
                else protectedD++;
            }

            else if (a[0].equals("public")) {
                if (result.equals("ALLOWED")) publicA++;
                else publicD++;
            }
        }

        return "private: " + privateA + " allowed / " + privateD + " denied | "
             + "default: " + defaultA + " allowed / " + defaultD + " denied | "
             + "protected: " + protectedA + " allowed / " + protectedD + " denied | "
             + "public: " + publicA + " allowed / " + publicD + " denied";
    }
}

class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {

        membershipId = membershipId.trim();

        if (membershipId.length() < 4)
            throw new IllegalArgumentException();

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}