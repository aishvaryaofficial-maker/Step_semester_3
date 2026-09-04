class BrokenLibraryMember {

    // These should NOT be static because every member
    // needs to have their own separate values.
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "City Library";
    static int memberCount = 1000;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class F4 {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember first = new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember second = new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(first.name);
        System.out.println(second.name);

        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember member1 = new LibraryMember("Aditi", 2);

        LibraryMember member2 = new LibraryMember("Rohan", 3);

        member1.printMemberCard();
        member2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}