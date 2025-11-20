package az.devlab.pdftextsearch.exception;

public class PdfNotFoundException extends RuntimeException {

    public PdfNotFoundException(Long id) {
        super("PDF document not found with id: " + id);
    }

    public PdfNotFoundException(String message) {
        super(message);
    }
}
