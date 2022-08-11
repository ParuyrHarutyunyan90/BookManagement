package books.exception;

public class AuthorNotFoundException extends Exception {
    AuthorNotFoundException() {

    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
