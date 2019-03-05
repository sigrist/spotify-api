package com.github.sigrist.spotify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

@DisplayName("Test Me implementation")
public class MeTest {
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);
	
	private SpotifyServerMock spotifyServerMock = new SpotifyServerMock();
	private Spotify spotify;

	@BeforeEach
	public void init() {
		SpotifyConfiguration configuration = SpotifyConfiguration.builder()
				.token("abc")
				.configuration("server", "http://localhost:8089")
				.build();
		spotify = Spotify.instance(configuration);
		
		this.wireMockRule.start();
	}
	
	@Test
	@DisplayName("Test if the me is not null")
	public void testMe() {
		// Simulate /me endpoint
		this.spotifyServerMock.me(wireMockRule);
		
		// get Me
		Me me = spotify.me();
		assertNotNull(me);
		
		assertEquals("BR", me.country());
		assertEquals("Paulo Sigrist", me.displayName());
		this.spotifyServerMock.verifyMe(wireMockRule);

	}
}
