package Default;

public class Book {
	private String bookId;
    private String title;
    private String authors;
    private int publicationYear;
    private String isbn;
    private boolean isAvailable;

    public Book(String bookId, String title, String authors, int publicationYear, String isbn, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Authors: " + authors + ", Publication Year: " + publicationYear + ", ISBN: " + isbn + ", Available: " + isAvailable;
    }
}
