package com.github.sigrist.spotify;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Spotify API Test")
public class SpotifyAPITest {

	@Test
	@DisplayName("Test to check the API instantiation")
	public void testCreateAPI() {
		SpotifyConfiguration configuration = new UnitTestSpotifyConfiguration();
		Spotify spotify = Spotify.instance(configuration);

		assertNotNull(spotify);
	}

}
