package Default;
import java.util.*;

public class LibSys {
	//create the data structures to store the object instances
	private static List<Membership> memberships = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();

    //constructor function where the menu is implemented and the other functions are called based on the users selection.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	//display of the available functionalities
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Membership Data");
            System.out.println("2. Add Library Data");
            System.out.println("3. Display Book Availability");
            System.out.println("4. Sort Books Alphabetically by Title");
            System.out.println("5. Sort Books by Publication Year");
            System.out.println("6. Search Book by Title");
            System.out.println("7. Count Total Members");
            System.out.println("8. Find Book with Earliest Publication Year");
            System.out.println("9. Find Book with Latest Publication Year");
            System.out.println("10. Loan a Book");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            // switch statement that handles the input from the user on what functionality he/she wishes to proceed with.
            switch (choice) {
                case 1:
                    addMembershipData(scanner);
                    break;
                case 2:
                    addLibraryData(scanner);
                    break;
                case 3:
                    displayBookAvailability();
                    break;
                case 4:
                    sortBooksByTitle();
                    break;
                case 5:
                    sortBooksByYear();
                    break;
                case 6:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    searchBookByTitle(title);
                    break;
                case 7:
                    System.out.println("Total members: " + memberships.size());
                    break;
                case 8:
                    findEarliestPublication();
                    break;
                case 9:
                    findLatestPublication();
                    break;
                case 10:
                    loanBook(scanner);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //function responsible to create a new membership 
    public static void addMembershipData(Scanner scanner) {
        System.out.print("Enter Membership ID: ");
        String membershipId = scanner.nextLine();
        
        //input of the users first name where data validation exists to check is the first name is in the correct format.
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        if (!firstName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. First name must contain only letters.");
            return;
        }
        
        //similar to the above (First name), but this time the last name must be entered.
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        if (!lastName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Last name must contain only letters.");
            return;
        }
        
        //input of the email address where validation also exists for the format( "@" and "." are required).
        System.out.print("Enter Email Address: ");
        String emailAddress = scanner.nextLine();
        if (!emailAddress.contains("@") || !emailAddress.contains(".")) {
            System.out.println("Invalid email format.");
            return;
        }
        //once data validation is complete the member is added to the list
        memberships.add(new Membership(membershipId, firstName, lastName, emailAddress));
        System.out.println("Membership added successfully.");
    }

    //function to add a book in the list.
    public static void addLibraryData(Scanner scanner) {
    	//user input.
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Authors: ");
        String authors = scanner.nextLine();

        System.out.print("Enter Publication Year: ");
        int publicationYear;
        //validating that the year is valid i.e.  non negative.
        try {
            publicationYear = Integer.parseInt(scanner.nextLine());
            if (publicationYear < 0 || publicationYear > 2025) {
            	System.out.println("Please enter a valid date");
            	return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Publication year must be an integer.");
            return;
        }

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        //the usre can set the availability of the book.
        System.out.print("Is the book available (true/false): ");
        boolean isAvailable;
        try {
            isAvailable = Boolean.parseBoolean(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Availability must be true or false.");
            return;
        }

        books.add(new Book(bookId, title, authors, publicationYear, isbn, isAvailable));
        System.out.println("Book added successfully.");
    }

    //function displaying the number of available and unavailable books
    public static void displayBookAvailability() {
        int availableBooks = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks++;
            }
        }
        int unavailableBooks = books.size() - availableBooks;
        System.out.println("Available books: " + availableBooks);
        System.out.println("Books out on loan: " + unavailableBooks);
    }
    
    //function that calls the quickSort() to sort the books in the list by title (alphabetically).
    static void sortBooksByTitle() {
        quickSort(books, 0, books.size() - 1, "title");
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    //Function theat calls the quickSort() to sort the books in the list by year earliest to oldest.
    static void sortBooksByYear() {
        quickSort(books, 0, books.size() - 1, "year");
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    //quickSort function, performs the sorting of the books in the list.
    private static void quickSort(List<Book> books, int low, int high, String sortBy) {
        if (low < high) {
            int pi = partition(books, low, high, sortBy);

            quickSort(books, low, pi - 1, sortBy);
            quickSort(books, pi + 1, high, sortBy);
        }
    }
    
    //The partition function is responsible for dividing the list relative to the pivot value and place the pivot value in its correct 
    //sorted position in the list, by ensuring that larger values are on its right and smaller values on its left.
    private static int partition(List<Book> books, int low, int high, String sortBy) {
        Book pivot = books.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = (sortBy.equals("title"))
                ? books.get(j).getTitle().compareToIgnoreCase(pivot.getTitle()) < 0 : books.get(j).getPublicationYear() > pivot.getPublicationYear();

            if (condition) {
                i++;
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }

        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);

        return i + 1;
    }

    //Function that goes through the list until the string passed as a parameter is found as the title of the book.
    public static void searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    //Function that finds the earliest publicated book in the list.
    public static void findEarliestPublication() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        Book earliest = books.get(0);
        for (Book book : books) {
            if (book.getPublicationYear() < earliest.getPublicationYear()) {
                earliest = book;
            }
        }
        System.out.println("Earliest publication: " + earliest);
    }

    //Function that finds the latest publicated book in the list
    public static void findLatestPublication() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        Book latest = books.get(0);
        for (Book book : books) {
            if (book.getPublicationYear() > latest.getPublicationYear()) {
                latest = book;
            }
        }
        System.out.println("Latest publication: " + latest);
    }
    
    //Function that takes the membership id, book id and the date that the book should be loaned. Ensures that the membership and book id's
    //are valid and that the book is available. Then checks the availability of the given book and if it is available then 
    //it loans it to the given membership id and sets its availability to false.
    public static void loanBook(Scanner scanner) {
        System.out.print("Enter Membership ID: ");
        String membershipId = scanner.nextLine();

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        System.out.print("Enter Loan Date (YYYY-MM-DD): ");
        String loanDate = scanner.nextLine();

        Membership member = null;
        for (Membership m : memberships) {
            if (m.getMembershipId().equals(membershipId)) {
                member = m;
                break;
            }
        }

        if (member == null) {
            System.out.println("Invalid membership ID.");
            return;
        }

        Book bookToLoan = null;
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                bookToLoan = book;
                break;
            }
        }

        if (bookToLoan == null) {
            System.out.println("Invalid book ID.");
            return;
        }

        if (!bookToLoan.isAvailable()) {
            System.out.println("The book is already on loan.");
            return;
        }

        bookToLoan.setAvailable(false);
        System.out.println("Book loaned successfully to Member ID: " + membershipId + " on " + loanDate);
    }
}
