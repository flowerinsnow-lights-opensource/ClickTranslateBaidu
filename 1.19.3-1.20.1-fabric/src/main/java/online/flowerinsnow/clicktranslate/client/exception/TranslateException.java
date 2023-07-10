package online.flowerinsnow.clicktranslate.client.exception;

public class TranslateException extends RuntimeException {
    public TranslateException() {
        super();
    }

    public TranslateException(String message) {
        super(message);
    }

    public TranslateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TranslateException(Throwable cause) {
        super(cause);
    }
}
