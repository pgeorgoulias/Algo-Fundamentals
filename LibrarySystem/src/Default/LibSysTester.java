package Default;
import java.util.*;

public class LibSysTester {

    public static void main(String[] args) {
        // Initialize LibraryManagement instance and Scanner for input simulation
        LibSys library = new LibSys();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Running Library Management Tests...");

        // Test Case 1: Add Membership Data
        System.out.println("\nTest Case 1: Add Membership Data");
        testAddMembership(library, "1", "Alice", "Smith", "alice.smith@example.com\n"); // Valid
        testAddMembership(library, "2", "Bob", "Brown123", "bob.brown@example.com"); // Invalid last name
        testAddMembership(library, "3", "", "Jones", "jones@example.com"); // Invalid first name (empty)
        testAddMembership(library, "4", "Charlie", "Davis", "charliedavis@domain"); // Invalid email

        // Test Case 2: Add Library Data
        System.out.println("\nTest Case 2: Add Library Data");
        testAddLibraryData(library, "101", "Java Programming", "John Doe", 2020, "1234567890", true); // Valid
        testAddLibraryData(library, "102", "Data Structures", "Jane Roe", -1, "0987654321", true); // Invalid year
        testAddLibraryData(library, "103", "", "Jane Roe", 2018, "9876543210", true); // Invalid title (empty)
        testAddLibraryData(library, "104", "Algorithms", "", 2019, "1122334455", true); // Invalid author (empty)

        // Test Case 3: Display Book Availability
        System.out.println("\nTest Case 3: Display Book Availability");
        LibSys.displayBookAvailability();

        // Test Case 4: Sort Books by Title
        System.out.println("\nTest Case 4: Sort Books by Title");
        LibSys.sortBooksByTitle();

        // Test Case 5: Sort Books by Publication Year
        System.out.println("\nTest Case 5: Sort Books by Publication Year");
        LibSys.sortBooksByYear();

        // Test Case 6: Search for a Book by Title
        System.out.println("\nTest Case 6: Search for a Book by Title");
        testSearchBookByTitle(library, "Java Programming"); // Valid
        testSearchBookByTitle(library, "Nonexistent Book"); // Invalid (not found)

        // Test Case 7: Loan a Book
        System.out.println("\nTest Case 7: Loan a Book");
        testLoanBook(library, "1", "101", "2025-01-01"); // Valid
        testLoanBook(library, "1", "101", "2025-01-02"); // Invalid (already on loan)
        testLoanBook(library, "99", "101", "2025-01-03"); // Invalid membership ID
        testLoanBook(library, "1", "999", "2025-01-04"); // Invalid book ID

        System.out.println("\nAll tests completed.");
    }

    // Test adding membership data
    private static void testAddMembership(LibSys library, String membershipId, String firstName, String lastName, String emailAddress) {
        System.out.println("Adding membership: " + membershipId + ", " + firstName + ", " + lastName + ", " + emailAddress);
        try {
            LibSys.addMembershipData(new Scanner(membershipId + "\n" + firstName + "\n" + lastName + "\n" + emailAddress));
        } catch (Exception e) {
            System.out.println("Error adding membership: " + e.getMessage());
        }
    }

    // Test adding library data
    private static void testAddLibraryData(LibSys library, String bookId, String title, String authors, int publicationYear, String isbn, boolean isAvailable) {
        System.out.println("Adding book: " + bookId + ", " + title + ", " + authors + ", " + publicationYear + ", " + isbn + ", " + isAvailable);
        try {
            LibSys.addLibraryData(new Scanner(bookId + "\n" + title + "\n" + authors + "\n" + publicationYear + "\n" + isbn + "\n" + isAvailable));
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // Test searching for a book by title
    private static void testSearchBookByTitle(LibSys library, String title) {
        System.out.println("Searching for book: " + title);
        try {
            LibSys.searchBookByTitle(title);
        } catch (Exception e) {
            System.out.println("Error searching for book: " + e.getMessage());
        }
    }

    // Test loaning a book
    private static void testLoanBook(LibSys library, String membershipId, String bookId, String loanDate) {
        System.out.println("Attempting to loan book ID " + bookId + " to membership ID " + membershipId + " on " + loanDate);
        try {
            LibSys.loanBook(new Scanner(membershipId + "\n" + bookId + "\n" + loanDate));
        } catch (Exception e) {
            System.out.println("Error loaning book: " + e.getMessage());
        }
    }
}
