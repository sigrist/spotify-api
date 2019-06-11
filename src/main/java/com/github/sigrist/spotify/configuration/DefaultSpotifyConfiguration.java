package com.github.sigrist.spotify.configuration;

import com.github.sigrist.spotify.SpotifyConfiguration;

/**
 * Default configuration.
 * @author sigrist
 */
public class DefaultSpotifyConfiguration implements SpotifyConfiguration {

    /** The user token to access the API. */
    private final String token;

    /**
     * Default constructor.
     * @param token The user token to access the API.
     */
    public DefaultSpotifyConfiguration(final String token) {
        this.token = token;
    }

    /**
     * Returns the default value.
     * @param key The configuration key.
     * @param defaultValue The default value if not found.
     * @return Always the defaultValue
     */
    @Override
    public String get(final String key, final String defaultValue) {
        return defaultValue;
    }

    @Override
    public String token() {
        return token;
    }

    @Override
    public String server() {
        return "https://api.spotify.com";
    }

}
