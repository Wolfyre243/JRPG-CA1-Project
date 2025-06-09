public class BookManagement {
    private Book[] bookStore;

    public BookManagement() {
        this.bookStore = new Book[50]; // TODO: Change to dynamically expanding data struct
    }

    // displayBooks

    // searchBookByTitle
    // regular exp?

    // addBook

    public void initialiseBooks(Book[] bookArr) {
        for (int i = 0; i < bookArr.length; i++) {
            bookStore[i] = bookArr[i];
        }
    }
}
