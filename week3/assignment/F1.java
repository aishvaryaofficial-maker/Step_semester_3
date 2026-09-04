class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // fineAmount() belongs to one book, so it is an instance method.
    // totalFineCollected() works on all books together, so it is static.
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue book : issues) {
            total += book.fineAmount();
        }

        return total;
    }
}

public class F1 {
    public static void main(String[] args) {

        BookIssue[] books = {
                new BookIssue("Clean Code", "Arun", 18),
                new BookIssue("Effective Java", "Rahul", 5),
                new BookIssue("Refactoring", "Priya", 0),
                new BookIssue("DSA Handbook", "Karthik", 21),
                new BookIssue("Design Patterns", "Meena", 9)
        };

        for (BookIssue book : books) {
            if (book.isSeverelyOverdue()) {
                System.out.println(book.title + " - " + book.daysOverdue
                        + " days - Severely overdue");
            } else {
                System.out.println(book.title + " - " + book.daysOverdue
                        + " days - OK");
            }
        }

        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(books));
    }
}