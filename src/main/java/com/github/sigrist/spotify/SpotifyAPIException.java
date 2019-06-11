package com.github.sigrist.spotify;

/**
 * Exception for any error in the API.
 * @author sigrist
 * @since 1.0.0
 */
public class SpotifyAPIException extends RuntimeException {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 6677780727301277421L;

    /**
     * Full constructor.
     * @param message The exception message.
     * @param cause   The root cause.
     */
    public SpotifyAPIException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Simple constructor.
     * @param message The exception message.
     */
    public SpotifyAPIException(String message) {
        super(message);
    }

}
