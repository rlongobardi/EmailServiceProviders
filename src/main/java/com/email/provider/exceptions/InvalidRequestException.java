package com.email.provider.exceptions;

public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 5936222077736739194L;

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(final String message) {
        super(message);
    }

    public InvalidRequestException(final Throwable cause) {
        super(cause);
    }
}
