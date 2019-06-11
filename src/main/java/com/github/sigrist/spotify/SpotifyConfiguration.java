package com.github.sigrist.spotify;

/**
 * Configuration class to create a Spotify Connection.
 * @author sigrist
 */
public interface SpotifyConfiguration {

    /**
     * Get a configuration value or the default value if not found.
     * @param key          The configuration key.
     * @param defaultValue The default value if not found.
     * @return The value or the default value.
     */
    String get(final String key, final String defaultValue);

    /**
     * @return the token.
     */
    public String token();

    /**
     * @return the server.
     */
    public String server();

}
