package be.vdab.frida.exceptions;

public class SnackNietGevondenException extends RuntimeException {
    private static final long SerialVersionUID = 1L;

    public SnackNietGevondenException() {
    }

    public SnackNietGevondenException(String message) {
        super(message);
    }
}
