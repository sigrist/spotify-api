package com.github.sigrist.spotify;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the creation of the API.
 * @author sigrist
 */
@DisplayName("Spotify API Test")
public class SpotifyAPITest {

    /**
     * Tests the API instantiation.
     */
    @Test
    @DisplayName("Test to check the API instantiation")
    public void testCreateAPI() {
        final SpotifyConfiguration configuration = new UnitTestSpotifyConfiguration();
        final Spotify spotify = Spotify.instance(configuration);

        assertNotNull(spotify);
    }

}
