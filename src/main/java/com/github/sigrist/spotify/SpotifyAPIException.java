package com.github.sigrist.spotify;

public class SpotifyAPIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6677780727301277421L;

	public SpotifyAPIException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpotifyAPIException(String message) {
		super(message);
	}

}
