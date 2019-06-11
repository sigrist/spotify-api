package com.github.sigrist.spotify;

/**
 * Implementation for the unit tests.
 * @author sigrist
 */
public class UnitTestSpotifyConfiguration implements SpotifyConfiguration {

    /**
     * Returns the defaultValue.
     */
    @Override
    public String get(String key, String defaultValue) {
        return defaultValue;
    }

    /**
     * Returns the value `defaultToken`.
     */
    @Override
    public String getToken() {
        return "defaultToken";
    }

    /**
     * Returns the value `http://localhost:8089`.
     */
    @Override
    public String getServer() {
        return "http://localhost:8089";
    }

}
